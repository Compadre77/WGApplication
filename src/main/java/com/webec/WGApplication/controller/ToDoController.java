package com.webec.WGApplication.controller;

import com.webec.WGApplication.model.entity.User;
import com.webec.WGApplication.service.ToDoService;
import com.webec.WGApplication.service.UserService;
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
    private final UserService userService;

    public ToDoController(ToDoService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping("/ämtli")
    public String todos(Model model){
        model.addAttribute("allTodos", service.getAllToDos());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("currentUser", ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
        return "todos";
    }
//    @GetMapping("/ämtli/{id}")
//    public String todosByID(Model model){
//        model.addAllAttributes("todosByID", service.getTodosByCurrentAssignee())
//    }

    @PostMapping("/ämtli")
    public String todos(@RequestParam @NotBlank String description,
                     @RequestParam @NotBlank int days,
                     @RequestParam @NotBlank int currentAssigneeId,
                     @RequestParam @NotBlank Date currentDeadline,
                     @RequestParam @NotBlank boolean done,
                     @RequestParam int[] userIDs){

        service.add(
                ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId(),
                description.strip(),
                days,
                currentAssigneeId,
                currentDeadline,
                done,
                userIDs
        );
        return "redirect:/ämtli";
    }

    @PostMapping("/ämtli/löschen/{id}")
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
