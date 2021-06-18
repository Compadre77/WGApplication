package com.webec.WGApplication.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webec.WGApplication.SampleDataAdder;
import com.webec.WGApplication.model.entity.User;
import com.webec.WGApplication.model.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    UserRepository repo;
    UserService service;

    public UserServiceTest() throws IOException{
        var mapper = new ObjectMapper().configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        var testUserFile = SampleDataAdder.class.getResource(SampleDataAdder.USER_JSON_FILE);
        var testUsers = mapper.readValue(testUserFile, new TypeReference<List<User>>() {});

        // create fake (mock) repo that returns the sample contacts from the file
        var repo = mock(UserRepository.class);
        when(repo.findAll()).thenReturn(testUsers);
        when(repo.findById(1)).thenReturn(Optional.of(testUsers.get(0)));
        this.repo = repo;
        this.service = new UserService(repo);
    }

    @Test
    public void testGetAllUsers(){
        Assert.assertEquals(4, service.getAllUsers().size());
    }

    @Test
    public void userName(){
        var users = service.getAllUsers();
        Assert.assertNotNull(users);
        Assert.assertFalse(users.isEmpty());
        Assert.assertEquals("Test", users.get(0).username);
    }
}
