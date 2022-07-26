package com.example.questionnaire;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class QuestionController {

    record QueryAnswer(String id, String like_meat, String like_veg, String like_idol) {}
    private List<QueryAnswer> queryAnswerList = new ArrayList<>();

    @GetMapping("/question")
    String question() {
        return "question";
    }

    @PostMapping("/confirm")
    public String confirm(@RequestParam("like_meat") String like_meat,
                                  @RequestParam("like_veg") String like_veg,
                                  @RequestParam("like_idol") String like_idol,
                                  Model model) {
        model.addAttribute("like_meat", like_meat);
        model.addAttribute("like_veg", like_veg);
        model.addAttribute("like_idol", like_idol);
        return "confirm";
    }

    @RequestMapping(value = "/question", params = "back", method = RequestMethod.POST)
    public String back(){
        return "question";
    }

    @RequestMapping(value = "/complete", params = "complete", method = RequestMethod.POST)
    public String complete() {
        return "complete";
    }
}