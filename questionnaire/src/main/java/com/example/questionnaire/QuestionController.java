package com.example.questionnaire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class QuestionController {
    private final QueryAnswerDao dao;

    @Autowired
    QuestionController(QueryAnswerDao dao) {
        this.dao = dao;
    }

    record QueryAnswer(String id, String like_meat, String like_veg, String like_idol) {}
    private List<QueryAnswer> queryAnswerList = new ArrayList<>();

    @RequestMapping(value = "/question", method = RequestMethod.GET)
    String question() {
        return "question";
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public String confirm(@Validated
                          @ModelAttribute QuestionAnswerModel questionAnswerModel,
                          BindingResult bindingResult,
                          Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("org.springframework.validation.BindingResult.questionAnswerModel", bindingResult);
            model.addAttribute("questionAnswerModel", questionAnswerModel);
            return "redirect:question";
        }
        model.addAttribute("like_meat", questionAnswerModel.getLike_meat());
        model.addAttribute("like_veg", questionAnswerModel.getLike_veg());
        model.addAttribute("like_idol", questionAnswerModel.getLike_idol());
        return "confirm";
    }

    @RequestMapping(value = "/complete", method = RequestMethod.POST)
    String complete(@RequestParam("like_meat") String like_meat,
                    @RequestParam("like_veg") String like_veg,
                    @RequestParam("like_idol") String like_idol) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        QueryAnswer queryAnswer = new QueryAnswer(id, like_meat, like_veg, like_idol);
        dao.add(queryAnswer);

        return "complete";
    }
}