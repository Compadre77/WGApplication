package com.webec.WGApplication.RESTController;

import com.webec.WGApplication.model.entity.ToDo;
import com.webec.WGApplication.service.ToDoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class ToDoRestController {

    ToDoService service;

    public ToDoRestController(ToDoService service){ this.service = service; }

    @GetMapping
    public List<ToDo> getAll(){
       return service.findAll();
    }
}
