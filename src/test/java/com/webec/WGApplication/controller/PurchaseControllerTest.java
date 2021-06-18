package com.webec.WGApplication.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webec.WGApplication.SampleDataAdder;
import com.webec.WGApplication.model.entity.Purchase;
import com.webec.WGApplication.model.repository.PurchaseRepository;
import com.webec.WGApplication.service.PurchaseService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.xmlunit.util.Mapper;

import java.io.IOException;
import java.util.List;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PurchaseControllerTest {

    PurchaseService service;
    PurchaseController controller;

    public PurchaseControllerTest() throws IOException {
        var mapper = new ObjectMapper().configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        var testPurchaseFile = SampleDataAdder.class.getResource(SampleDataAdder.PURCHASES_JSON_FILE);
        var testPurchases = mapper.readValue(testPurchaseFile, new TypeReference<List<Purchase>>() {});

        // create fake (mock) repo that returns the sample contacts from the file
        var repo = mock(PurchaseRepository.class);
        when(repo.findAll()).thenReturn(testPurchases);

        this.service = new PurchaseService(repo);
        this.controller= new PurchaseController(service);
    }

    Model model = new ConcurrentModel();

    @Test
    public void testNumberOfPurchases(){
        controller.purchases(model);
        Assert.assertEquals(14, service.getAllPurchases().size());
    }
}
