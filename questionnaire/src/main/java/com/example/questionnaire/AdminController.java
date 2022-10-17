package com.example.questionnaire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {
    private final AdminDao dao;

    @Autowired
    AdminController(AdminDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    String login(Model model) {
        model.addAttribute("adminModel", new AdminModel());
        return "login";
    }

    @RequestMapping(value = "/view", method = RequestMethod.POST)
    String view(@Validated
                @ModelAttribute("adminModel")AdminModel adminModel,
                BindingResult bindingResult,
                Model model) {
        for (var admin_list : dao.adminList()) {
            if (bindingResult.hasErrors()) {
                return "view";
            } else if (adminModel.getName() == admin_list.name &&
                       adminModel.getPassword() == admin_list.password) {
                return "view";
            } else {
                return "view";
            }
        }
        return "view";
    }
}
