//package com.webec.WGApplication.controller;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.webec.WGApplication.SampleDataAdder;
//import com.webec.WGApplication.controller.ToDoController;
//import com.webec.WGApplication.model.entity.Purchase;
//import com.webec.WGApplication.model.entity.ToDo;
//import com.webec.WGApplication.model.entity.User;
//import com.webec.WGApplication.model.repository.PurchaseRepository;
//import com.webec.WGApplication.model.repository.ToDoRepository;
//import com.webec.WGApplication.model.repository.UserRepository;
//import com.webec.WGApplication.service.ToDoService;
//import com.webec.WGApplication.service.UserService;
//import org.junit.Assert;
//import org.junit.Test;
//import org.mockito.Mockito;
//import org.springframework.ui.ConcurrentModel;
//import org.springframework.ui.Model;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Optional;
//
//import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
//import static com.webec.WGApplication.SampleDataAdder.TODO_JSON_FILE;
//import static com.webec.WGApplication.SampleDataAdder.USER_JSON_FILE;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//public class ToDoControllerTest {
//
//    ToDoService service;
//    UserService userService;
//    ToDoController controller;
//
//    public ToDoControllerTest() throws IOException{
//        // erstelle Liste mit data
//        var todoMapper = new ObjectMapper().configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
//        var testTodoFile = SampleDataAdder.class.getResource(TODO_JSON_FILE);
//        var testTodos = todoMapper.readValue(testTodoFile, new TypeReference<List<ToDo>>() {});
//
//        // erstelle Liste mit user-data
//        var userMapper = new ObjectMapper().configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
//        var testUserFile = SampleDataAdder.class.getResource(USER_JSON_FILE);
//        var testUsers = userMapper.readValue(testUserFile, new TypeReference<List<User>>() {});
//
//        // Repo befüllen
//        var todoRepo = mock(ToDoRepository.class);
//        when(todoRepo.findAll()).thenReturn(testTodos);
//        when(todoRepo.findById(1)).thenReturn(Optional.of(testTodos.get(0)));
//
//
//        // userRepo1 befüllen
//        var userRepo1 = mock(UserRepository.class);
//        when(userRepo1.findAll()).thenReturn(testUsers);
//        when(userRepo1.findById(1)).thenReturn(Optional.of(testUsers.get(0)));
//
//
//        // userRepo2 befüllen
//        var userRepo2 = mock(UserRepository.class);
//        when(userRepo2.findAll()).thenReturn(testUsers);
//        when(userRepo2.findById(1)).thenReturn(Optional.of(testUsers.get(0)));
//
//
//        UserService forTodoService = new UserService(userRepo1);
//
//        this.userService = new UserService(userRepo2);
//        this.service = new ToDoService(todoRepo, forTodoService);
//        this.controller = new ToDoController(service, userService);
//    }
//
//
//
//    @Test
//    public void testMethodTodos(){
//        controller.deleteTodo(1);
//        Assert.assertEquals(3, service.getAllToDos().size());
//    }
//
//
//}
//
//
//
