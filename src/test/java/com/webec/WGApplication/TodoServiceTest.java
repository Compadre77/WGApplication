package com.webec.WGApplication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webec.WGApplication.model.entity.ToDo;
import com.webec.WGApplication.model.repository.ToDoRepository;
import com.webec.WGApplication.service.ToDoService;
import com.webec.WGApplication.service.UserService;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.webec.WGApplication.SampleDataAdder.TODO_JSON_FILE;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class TodoServiceTest {

    ToDoService service;
    UserService userService;

    TodoServiceTest() throws IOException{
        var mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        var sampleContacts = mapper.readValue(SampleDataAdder.class.getResource(TODO_JSON_FILE),
                new TypeReference<List<ToDo>>() {});
        ToDoRepository mockRepo = mock(ToDoRepository.class);

        when(mockRepo.findAll()).thenReturn(sampleContacts);

        this.service = new ToDoService(mockRepo,userService);
    }

    @Test
    public void findBillAbsent(){
        var bill = service.findToDo(9983720);
        assertTrue(bill.isEmpty());
    }
}
