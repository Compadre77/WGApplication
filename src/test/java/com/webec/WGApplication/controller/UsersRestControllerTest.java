package com.webec.WGApplication.controller;

import com.webec.WGApplication.model.entity.ToDo;
import com.webec.WGApplication.model.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UsersRestControllerTest {

    @Autowired
    TestRestTemplate rest;

    @Test
    public void getAll(){
        var body = rest.getForObject("/api/users", String.class);
        Assertions.assertThat(body).contains("Daniel");
    }
}
