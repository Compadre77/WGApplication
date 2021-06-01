package com.webec.WGApplication.controller;

import com.webec.WGApplication.service.ToDoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

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

    @PostMapping("/ämtli")
    public int todos(@RequestParam @NotBlank String description,
                     @RequestParam @NotBlank int days,
                     @RequestParam @NotBlank int currentAssignee,
                     @RequestParam @NotBlank Date currentDeadline,
                     @RequestParam int userIDs){

        var created = service.add(
                description.strip(),
                days,
                currentAssignee,
                currentDeadline,
                userIDs);
        System.out.println(created);
        return created.getId();
    }
}
