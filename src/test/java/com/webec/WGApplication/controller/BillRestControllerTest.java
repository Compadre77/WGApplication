package com.webec.WGApplication.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BillRestControllerTest {

    @Autowired
    TestRestTemplate rest;

    @Test
    public void getAll(){
        var body = rest.getForObject("/api/bills", String.class);
        Assertions.assertThat(body).contains("Einkauf vom 11.4.2020");
    }
}
