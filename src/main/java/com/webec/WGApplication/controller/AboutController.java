package com.webec.WGApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    public AboutController(){}
    @GetMapping("/about")
    public String todos(Model model){
        return "about";
    }
}
