//package com.webec.WGApplication;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.webec.WGApplication.controller.ToDoController;
//import com.webec.WGApplication.model.entity.ToDo;
//import com.webec.WGApplication.model.entity.User;
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
//
//import static com.webec.WGApplication.SampleDataAdder.TODO_JSON_FILE;
//
//public class ToDoControllerTest {
//
//    ToDoService service;
//    UserService userService;
//    User user = new User("Test", "test", null);
//    List<User> sampleUser = List.of(user);
//
//    public ToDoControllerTest() throws IOException{
//
//        UserRepository userRepo = Mockito.mock(UserRepository.class);
//        Mockito.when(userRepo.findAll()).thenReturn(sampleUser);
//        this.userService = new UserService(userRepo);
//
//        var mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        var sampleTodos = mapper.readValue(SampleDataAdder.class.getResource(TODO_JSON_FILE),
//                new TypeReference<List<ToDo>>() {});
//        ToDoRepository todoRepo = Mockito.mock(ToDoRepository.class);
//        Mockito.when(todoRepo.findAll()).thenReturn(sampleTodos);
//        this.service = new ToDoService(todoRepo,userService);
//    }
//
//    ToDoController controller = new ToDoController(service,userService);
//    Model model = new ConcurrentModel();
//
//    @Test
//    public void testAllUsers(){
//        controller.todos(model);
//        Assert.assertEquals();
//    }
//}
//
//
//
