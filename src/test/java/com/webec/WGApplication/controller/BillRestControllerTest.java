package com.webec.WGApplication.controller;

import com.webec.WGApplication.model.entity.Bill;
import com.webec.WGApplication.model.entity.Purchase;
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
public class BillRestControllerTest {

    @Autowired
    TestRestTemplate rest;

    @Test
    public void getAll(){
        var body = rest.getForObject("/api/bills", String.class);
        Assertions.assertThat(body).contains("Einkauf vom 11.4.2020");
    }

    @Test
    public void getValuesOfBills(){
        var response = rest.exchange("/api/bills", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Bill>>() {
                });
        var bills = response.getBody();
        Assert.assertEquals(4, bills.size());
        var first = bills.get(0);
        Assert.assertEquals("Coop Einkauf 23.4.2012", first.getDescription());
    }
}
