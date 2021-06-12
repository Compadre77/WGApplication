package com.webec.WGApplication.service;


import com.webec.WGApplication.model.PurchaseEntry;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.webec.WGApplication.model.entity.Purchase;
import com.webec.WGApplication.model.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepo;

    public PurchaseService(PurchaseRepository repo) {
        this.purchaseRepo = repo;
    }

    public List<PurchaseEntry> getAllPurchases() {
        return purchaseRepo.findAll().stream()
                .map(p -> createPurchaseEntry(p))
                .collect(Collectors.toList());
    }

    private PurchaseEntry createPurchaseEntry(Purchase p) {
        var entry = new PurchaseEntry(
                p.getId(),
                p.getAmount(),
                p.getDescription(),
                p.isChecked());
        return entry;
    }

    public Purchase add(
            int amount,
            String description
    ) {
        var purchase = new Purchase();
        purchase.setAmount(amount);
        purchase.setDescription(description);
        purchase.setChecked(false);

        return purchaseRepo.save(purchase);
    }

    public Optional<Purchase> findPurchase(int id) {
        return purchaseRepo.findById(id);
    }

    public void delete(Purchase purchase){
        purchaseRepo.delete(purchase);
    }


    public void update(Purchase purchase) {
        purchaseRepo.save(purchase);
    }
}