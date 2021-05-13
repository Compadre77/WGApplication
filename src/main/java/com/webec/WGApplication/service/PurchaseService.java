package com.webec.WGApplication.service;

import com.webec.WGApplication.model.PurchaseEntry;

import java.util.List;
import static java.util.stream.Collectors.toList;

import com.webec.WGApplication.model.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    private final PurchaseRepository repo;

    public PurchaseService(PurchaseRepository repo) { this.repo = repo; }

    public List<PurchaseEntry> getAllPurchases() {
        return repo.findAll().stream().map(p -> new PurchaseEntry(
                    p.getId(),
                    p.getDescription(),
                    p.isChecked()))
                .collect(toList());
    }
}
