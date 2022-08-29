package com.example.questionnaire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @RequestMapping(value = "/question", method = RequestMethod.GET)
    String question(Model model) {
        if (!model.containsAttribute("questionAnswerModel")) {
            model.addAttribute("questionAnswerModel", new QuestionAnswerModel());
        }
        return "question";
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public String confirm(@Validated
                          @ModelAttribute("questionAnswerModel") QuestionAnswerModel questionAnswerModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes,
                          Model model) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.questionAnswerModel", bindingResult);
            redirectAttributes.addFlashAttribute("questionAnswerModel", questionAnswerModel);
            return "question";
        }
        model.addAttribute("questionAnswerModel", questionAnswerModel);
        return "confirm";
    }

    @RequestMapping(value = "/complete", method = RequestMethod.POST)
    String complete(@Validated
                    @ModelAttribute("questionAnswerModel") QuestionAnswerModel questionAnswerModel,
                    BindingResult bindingResult,
                    RedirectAttributes redirectAttributes,
                    Model model) {
        dao.add(questionAnswerModel);
        return "complete";
    }
}