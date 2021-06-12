package com.webec.WGApplication.controller;

import com.webec.WGApplication.model.entity.User;
import com.webec.WGApplication.service.ToDoService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.Date;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
public class ToDoController {
    private final ToDoService service;

    public ToDoController(ToDoService service){ this.service = service; }

    @GetMapping("/ämtli")
    public String todos(Model model){
        model.addAttribute("allTodos", service.getAllToDos());
        model.addAttribute("billCount", service.getAllToDos().size());
        return "todos";
    }

    @PostMapping("/ämtli")
    public String todos(@RequestParam @NotBlank String description,
                     @RequestParam @NotBlank int days,
                     @RequestParam @NotBlank int currentAssignee,
                     @RequestParam @NotBlank Date currentDeadline,
                     @RequestParam int[] userIDs){

        service.add(
                ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId(),
                description.strip(),
                days,
                currentAssignee,
                currentDeadline,
                userIDs
        );
        return "redirect:/ämtli";
    }

    @PostMapping("/finanzen/löschen/{id}")
    public String deleteContact(@PathVariable int id) {
        var todo = service.findToDo(id).orElseThrow(ToDoController.ToDoNotFound::new);
        service.delete(todo);
        return "redirect:/ämtli";
    }

    @ExceptionHandler(ToDoController.ToDoNotFound.class)
    @ResponseStatus(NOT_FOUND)
    public String notFound(Model model) {
        model.addAttribute("todos", service.getAllToDos());
        model.addAttribute("errorMessage", "ToDo not found");
        return "ämtli";
    }
    private static class ToDoNotFound extends RuntimeException {}

}
