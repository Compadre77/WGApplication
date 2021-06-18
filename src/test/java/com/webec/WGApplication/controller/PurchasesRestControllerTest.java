package com.webec.WGApplication.controller;

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
public class PurchasesRestControllerTest {

    @Autowired
    TestRestTemplate rest;

    @Test
    public void getAll(){
        var body = rest.getForObject("/api/purchases", String.class);
        Assertions.assertThat(body).contains("Butter");
    }

    @Test
    public void getValuesOfPurchases(){
        var response = rest.exchange("/api/purchases", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Purchase>>() {
                });
        var purchases = response.getBody();
        Assert.assertEquals(14, purchases.size());
        var first = purchases.get(0);
        Assert.assertEquals("Butter", first.getDescription());
    }

}
