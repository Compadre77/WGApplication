package com.webec.WGApplication.service;

import com.webec.WGApplication.model.BillEntry;
import com.webec.WGApplication.model.entity.Bill;
import com.webec.WGApplication.model.enums.BillStatus;
import com.webec.WGApplication.model.repository.BillRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public Bill add(
            String description,
            double amount,
            int[] userIDs,
            Boolean isFix
    ) {
        List<Integer> users = new ArrayList<>();
        for (int i = 0; i < userIDs.length; i++) {
            users.add(userIDs[i]);
        }
        var bill = new Bill();
        bill.setDescription(description);
        bill.setAmount(amount);
        bill.setFix(isFix);
        bill.setBillStatus(BillStatus.NEGATIV);
        bill.setUserIDs(users);
        bill.setDate(new Date());
        bill.setCreator(1);
        return repo.save(bill); // 'save' might return new object
    }

    public Optional<Bill> findBill(int id) {
        return repo.findById(id);
    }

    public void delete(Bill bill) {
        repo.delete(bill);
    }
}

