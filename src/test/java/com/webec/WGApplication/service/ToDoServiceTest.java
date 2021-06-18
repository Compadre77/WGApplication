package com.webec.WGApplication.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webec.WGApplication.SampleDataAdder;
import com.webec.WGApplication.model.entity.ToDo;
import com.webec.WGApplication.model.entity.User;
import com.webec.WGApplication.model.repository.ToDoRepository;
import com.webec.WGApplication.model.repository.UserRepository;
import com.webec.WGApplication.service.ToDoService;
import com.webec.WGApplication.service.UserService;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.webec.WGApplication.SampleDataAdder.TODO_JSON_FILE;
import static com.webec.WGApplication.SampleDataAdder.USER_JSON_FILE;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ToDoServiceTest {

    ToDoRepository toDoRepository;
    UserRepository userRepository;
    UserService userService;
    ToDoService service;

    public ToDoServiceTest() throws IOException {
        var mapper = new ObjectMapper().configure(FAIL_ON_UNKNOWN_PROPERTIES, false);

        var sampleToDosFile = SampleDataAdder.class.getResource(TODO_JSON_FILE);
        var sampleToDos = mapper.readValue(sampleToDosFile, new TypeReference<List<ToDo>>() {
        });

        // create fake repo
        var todoRepo = mock(ToDoRepository.class);
        when(todoRepo.findAll()).thenReturn(sampleToDos);

        var sampleUsersFile = SampleDataAdder.class.getResource(USER_JSON_FILE);
        var sampleUsers = mapper.readValue(sampleUsersFile, new TypeReference<List<User>>() {
        });
        UserRepository userRepo = mock(UserRepository.class);
        when(userRepo.findAll()).thenReturn(sampleUsers);

        this.userRepository = userRepo;
        this.toDoRepository = todoRepo;
        System.out.println(userRepo.findAll().size());
        System.out.println(todoRepo.findAll().size());

        userService = new UserService(userRepository);
        service = new ToDoService(toDoRepository, userService);
        System.out.println(userRepository.findAll().size());
        System.out.println(toDoRepository.findAll().size());
    }

    @Test
    public void todoTest(){
        var todoList= service.getAllToDos();
        Assert.assertTrue(true);
    }
}
