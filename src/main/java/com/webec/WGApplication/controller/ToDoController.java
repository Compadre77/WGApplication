package com.webec.WGApplication.controller;

import com.webec.WGApplication.model.entity.User;
import com.webec.WGApplication.service.ToDoService;
import com.webec.WGApplication.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
public class ToDoController {
    private final ToDoService service;
    private final UserService userService;

    public ToDoController(ToDoService service, UserService userService) { this.service = service; this.userService = userService;  }

    @GetMapping("/todos")
    public String todos(Model model){
        model.addAttribute("allTodos", service.getAllToDos());
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("toDoService", service);
        model.addAttribute("currentUser", ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
        return "todos";
    }

    @PostMapping("/todos")
    public String todos(@RequestParam @NotBlank String description,
                        @RequestParam @NotBlank int days,
                        @RequestParam @NotBlank String currentDeadline,
                        @RequestParam @NotBlank int[] userIDs) throws ParseException {
        Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(currentDeadline);

        service.add(
                description.strip(),
                days,
                parsedDate,
                ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId(),
                userIDs
        );
        return "redirect:/todos";
    }

    @PostMapping("/todos/update/{id}")
    public String updateTodo(@PathVariable int id) {
        var todo = service.findToDo(id).orElseThrow(ToDoController.ToDoNotFound::new);
        service.updateTodo(todo);
        return "redirect:/todos";
    }

    @PostMapping("/todos/löschen/{id}")
    public String deleteTodo(@PathVariable int id) {
        var todo = service.findToDo(id).orElseThrow(ToDoController.ToDoNotFound::new);
        service.delete(todo);
        return "redirect:/todos";
    }

    @ExceptionHandler(ToDoController.ToDoNotFound.class)
    @ResponseStatus(NOT_FOUND)
    public String notFound(Model model) {
        model.addAttribute("todos", service.getAllToDos());
        model.addAttribute("errorMessage", "ToDo not found");
        return "todos";
    }
    private static class ToDoNotFound extends RuntimeException {}

}
