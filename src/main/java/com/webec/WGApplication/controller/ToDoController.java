package com.webec.WGApplication.controller;

import com.webec.WGApplication.service.ToDoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ToDoController {
    private final ToDoService service;

    public ToDoController(ToDoService service){ this.service = service; }
    @GetMapping("/ämtli")
    public String todos(Model model){
        model.addAttribute("allTodos", service.getAllTodos());
        model.addAttribute("billCount", service.getAllTodos().size());
        return "todos";
    }
}
