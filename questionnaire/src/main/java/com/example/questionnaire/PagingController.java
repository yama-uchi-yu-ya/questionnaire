package com.example.questionnaire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PagingController {

    @Autowired
    ViewAnswerRepository repository;

    @RequestMapping("/view")
    public String showAnswerPagingList(Model model, Pageable pageable) {
        Page<ViewAnswer> pageList = repository.findAll(pageable);
        List<ViewAnswer> answerList = pageList.getContent();

        model.addAttribute("pages", pageList);
        model.addAttribute("answers", answerList);
        return "view";
    }
}
