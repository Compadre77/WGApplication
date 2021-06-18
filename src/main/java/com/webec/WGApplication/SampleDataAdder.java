package com.webec.WGApplication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webec.WGApplication.model.entity.Bill;
import com.webec.WGApplication.model.entity.Purchase;
import com.webec.WGApplication.model.entity.ToDo;
import com.webec.WGApplication.model.repository.BillRepository;
import com.webec.WGApplication.model.repository.PurchaseRepository;
import com.webec.WGApplication.model.repository.ToDoRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@ConditionalOnProperty("data.add-sample-data")
public class SampleDataAdder {

    public static final String BILLS_JSON_FILE = "bills.json";
    public static final String PURCHASES_JSON_FILE = "purchases.json";
    public static final String TODO_JSON_FILE = "ToDos.json";
    public static final String USER_JSON_FILE = "users.json";

    private final ObjectMapper mapper;
    private final BillRepository billRepo;
    private final PurchaseRepository purchaseRepo;
    private final ToDoRepository toDoRepo;

    public SampleDataAdder(ObjectMapper mapper, BillRepository billRepo, PurchaseRepository purchaseRepo, ToDoRepository toDoRepo) {
        this.mapper = mapper;
        this.billRepo = billRepo;
        this.purchaseRepo = purchaseRepo;
        this.toDoRepo = toDoRepo;
    }
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) throws IOException {
        if (billRepo.findAll().isEmpty()) {
            var sampleBills = mapper.readValue(SampleDataAdder.class.getResource(BILLS_JSON_FILE),
                    new TypeReference<List<Bill>>() {});
            billRepo.saveAll(sampleBills);
        }
        if (purchaseRepo.findAll().isEmpty()) {
            var samplePurchases = mapper.readValue(SampleDataAdder.class.getResource(PURCHASES_JSON_FILE),
                    new TypeReference<List<Purchase>>() {});
            purchaseRepo.saveAll(samplePurchases);
        }
        if (toDoRepo.findAll().isEmpty()) {
            var sampleTodos = mapper.readValue(SampleDataAdder.class.getResource(TODO_JSON_FILE),
                    new TypeReference<List<ToDo>>() {});
            toDoRepo.saveAll(sampleTodos);
        }
    }
}
