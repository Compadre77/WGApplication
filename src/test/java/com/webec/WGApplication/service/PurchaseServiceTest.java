package com.webec.WGApplication.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webec.WGApplication.SampleDataAdder;
import com.webec.WGApplication.model.entity.Purchase;
import com.webec.WGApplication.model.repository.PurchaseRepository;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static java.util.stream.IntStream.rangeClosed;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PurchaseServiceTest {

    PurchaseService service;

    PurchaseServiceTest() throws IOException {
        var mapper = new ObjectMapper().configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        var testPurchaseFile = SampleDataAdder.class.getResource(SampleDataAdder.PURCHASES_JSON_FILE);
        var testPurchases = mapper.readValue(testPurchaseFile, new TypeReference<List<Purchase>>() {});

        // create fake (mock) repo that returns the sample contacts from the file
        var repo = mock(PurchaseRepository.class);
        when(repo.findAll()).thenReturn(testPurchases);
        service = new PurchaseService(repo);
    }

    @Test
    void contactListIds() {
        var purchasesList = service.getAllPurchases();
        assertNotNull(purchasesList);
    }
}
