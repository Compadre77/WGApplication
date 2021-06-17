package com.webec.WGApplication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Set;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.webec.WGApplication.SampleDataAdder.TODO_JSON_FILE;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ToDoServiceTest {

    ToDoService service;
    UserService userService;
    User user = new User("Test", "test", Set.of("ROLE_ADMIN"));
    List<User> sampleUser = List.of(user);

    public ToDoServiceTest() throws IOException {
        var todoMapper = new ObjectMapper().configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        var sampleToDosFile = SampleDataAdder.class.getResource(TODO_JSON_FILE);
        var sampleToDos = todoMapper.readValue(sampleToDosFile, new TypeReference<List<ToDo>>() {
        });

        // create fake repo
        var todoRepo = mock(ToDoRepository.class);
        when(todoRepo.findAll()).thenReturn(sampleToDos);

//        var userMapper = new ObjectMapper().configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        UserRepository userRepo = mock(UserRepository.class);
        when(userRepo.findAll()).thenReturn(sampleUser);
        this.userService = new UserService(userRepo);

        service = new ToDoService(todoRepo,userService);
    }

    @Test
    public void todoTest(){
        var todoList= service.getAllToDos();
        for (int i =0; i<todoList.size();i++){
            System.out.println("Test");
        }
        Assert.assertTrue(true);
    }
}
