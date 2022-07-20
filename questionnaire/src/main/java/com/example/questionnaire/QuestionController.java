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
    private List<QueryAnswer> queryAnswer = new ArrayList<>();

    @RequestMapping("/question")
    public String question() {

        return "question";
    }

    @GetMapping("/question")
    String confirm() {
        return "question";
    }

    @PostMapping("/confirm")
    public String question_submit(@RequestParam("like_meat") String like_meat,
                                  @RequestParam("like_veg") String like_veg,
                                  @RequestParam("like_idol") String like_idol,
                                  @ModelAttribute Model model) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        QueryAnswer answer = new QueryAnswer(id, like_meat, like_veg, like_idol);
        queryAnswer.add(answer);
        model.addAttribute("query_answer", queryAnswer);
        return "confirm";
    }
}