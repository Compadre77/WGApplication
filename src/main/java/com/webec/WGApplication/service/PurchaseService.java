package com.webec.WGApplication.service;

import com.webec.WGApplication.model.PurchaseEntry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.webec.WGApplication.model.entity.Purchase;
import com.webec.WGApplication.model.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    private final PurchaseRepository repo;

    public PurchaseService(PurchaseRepository repo) { this.repo = repo; }

    public List<PurchaseEntry> getAllPurchases() {
        return repo.findAll().stream()
                .map(p -> new PurchaseEntry(
                    p.getId(),
                    p.getDescription(),
                    p.isChecked()))
                .collect(Collectors.toList());
    }

    public Purchase add(String description, int days, int currentAssignee, Date currentDeadline, int userID) {
        List<Integer> userIDs = new ArrayList<>();
        userIDs.add(userID);
        var purchase = new Purchase();
        return repo.save(purchase); // 'save' might return new object
    }
}
