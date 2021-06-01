package com.webec.WGApplication.service;

import com.webec.WGApplication.model.PurchaseEntry;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

import com.webec.WGApplication.model.entity.Purchase;
import com.webec.WGApplication.model.enums.BillStatus;
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

    public Purchase add(String description, boolean checked){
        var purchase = new Purchase();
        purchase.setDescription(description);
        purchase.setChecked(checked);
        return add(purchase);
    }

    public Purchase add(Purchase purchase){
        repo.add(purchase);
        return purchase;
    }
}
