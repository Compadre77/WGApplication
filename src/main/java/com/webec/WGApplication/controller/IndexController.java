package com.webec.WGApplication.controller;

import com.webec.WGApplication.model.entity.User;
import com.webec.WGApplication.service.BillService;
import com.webec.WGApplication.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    private final BillService billservice;

    public IndexController(BillService billservice) {this.billservice = billservice; }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("currentUser", ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        return "index";
    }
}
