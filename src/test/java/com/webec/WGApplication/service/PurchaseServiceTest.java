package com.webec.WGApplication.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webec.WGApplication.SampleDataAdder;
import com.webec.WGApplication.model.entity.Purchase;
import com.webec.WGApplication.model.repository.PurchaseRepository;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static java.util.stream.IntStream.rangeClosed;
import static org.junit.jupiter.api.Assertions.*;
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
        when(repo.findById(1)).thenReturn(Optional.of(testPurchases.get(0)));

        service = new PurchaseService(repo);
    }

    @Test
    void testPurchaseIds(){
        var purchaseList = service.getAllPurchases();
        assertNotNull(purchaseList);
        var ids = purchaseList.stream()
                .mapToInt(p -> p.id)
                .toArray();
        assertArrayEquals(rangeClosed(1, 14).toArray(), ids);
    }

    @Test
    void testPurchaseValues(){

        var purchaseList = service.getAllPurchases();
        assertNotNull(purchaseList);
        assertFalse(purchaseList.isEmpty());
        var values = purchaseList.get(0);
        assertEquals(1, values.id);
        assertEquals(1, values.amount);
        assertEquals("Butter",  values.description);
        assertFalse(values.checked);

    }
}
