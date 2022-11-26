package com.example.questionnaire.controller;

import com.example.questionnaire.dao.AdminDao;
import com.example.questionnaire.entity.ViewAnswer;
import com.example.questionnaire.model.AdminModel;
import com.example.questionnaire.repository.ViewAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Objects;

@Controller
public class AdminController {
    private final AdminDao dao;

    @Autowired
    AdminController(AdminDao dao) {
        this.dao = dao;
    }

    @Autowired
    ViewAnswerRepository repository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    String login(Model model) {
        model.addAttribute("adminModel", new AdminModel());
        return "login";
    }
    
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    String check(@Validated
                 @ModelAttribute("adminModel")AdminModel adminModel,
                 BindingResult bindingResult) {
        dao.checkOne(adminModel);
        if (bindingResult.hasErrors()) {
            System.out.println("バリデーションエラーだよ");
            return "login";
        } else if (Objects.isNull(dao.checkOne(adminModel))) {
            System.out.println("ID・パスワードが違うよ");
            return "login";
        }
        System.out.println("ログイン成功！");
        return "view";
    }
    
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    String view(@ModelAttribute("adminModel")Model model, Pageable pageable) {
        Page<ViewAnswer> pageList = repository.findAll(pageable);
        List<ViewAnswer> answerList = pageList.getContent();
        model.addAttribute("pages", pageList);
        model.addAttribute("answers", answerList);
        return "view";
    }
}
