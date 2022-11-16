package com.example.questionnaire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

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

    @RequestMapping(value = "/view", method = RequestMethod.POST)
    String view(@ModelAttribute("adminModel")AdminModel adminModel, Model model, Pageable pageable) {
        for (var admin_list : dao.adminList()) {
            if (adminModel.getName().equals(admin_list.name) &&
                adminModel.getPassword().equals(admin_list.password)) {
                Page<ViewAnswer> pageList = repository.findAll(pageable);
                List<ViewAnswer> answerList = pageList.getContent();

                model.addAttribute("pages", pageList);
                model.addAttribute("answers", answerList);
                return "view";
            }
        }
        return "login";
    }
}
