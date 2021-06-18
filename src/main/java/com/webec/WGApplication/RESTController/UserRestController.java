package com.webec.WGApplication.RESTController;

import com.webec.WGApplication.model.entity.User;
import com.webec.WGApplication.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    UserService service;

    public UserRestController(UserService service){ this.service = service; }

    @GetMapping
    public List<User> getAll(){
        return service.findAll();
    }
}
