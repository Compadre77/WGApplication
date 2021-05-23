package com.webec.WGApplication.service;

import com.webec.WGApplication.model.BillEntry;
import com.webec.WGApplication.model.entity.Bill;
import com.webec.WGApplication.model.repository.BillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillService {
    private final BillRepository repo;

    public BillService(BillRepository repo) {
        this.repo = repo;
    }

    public List<BillEntry> getAllBills() {
        return repo.findAll().stream()
                .map(b -> new BillEntry(
                        b.getId(),
                        b.getDate(),
                        b.getCreator(),
                        b.getDescription(),
                        b.getAmount(),
                        b.getBillStatus(),
                        b.getUserIDs(),
                        b.isFix()))
                .collect(Collectors.toList());
    }

    public Bill add(Bill bill) {
        return repo.save(bill);
    }
}
