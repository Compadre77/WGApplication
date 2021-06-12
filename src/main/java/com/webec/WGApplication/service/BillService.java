package com.webec.WGApplication.service;

import com.webec.WGApplication.model.BillEntry;
import com.webec.WGApplication.model.entity.Bill;
import com.webec.WGApplication.model.enums.BillStatus;
import com.webec.WGApplication.model.repository.BillRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BillService {
    private final BillRepository repo;
    private final UserService userService;

    public BillService(BillRepository repo, UserService userService) {
        this.repo = repo;
        this.userService = userService;
    }

    public List<BillEntry> getAllBills() {
        return repo.findAll().stream()
                .map(b -> createBillEntry(b))
                .collect(Collectors.toList());
    }

    private BillEntry createBillEntry(Bill b) {
        var entry = new BillEntry(
                b.getId(),
                b.getDate(),
                b.getCreator(),
                b.getDescription(),
                b.getAmount(),
                b.getBillStatus(),
                b.getUserIDs(),
                b.isFix());
        entry.users = new ArrayList<>();
        for (int i = 0; i < b.getUserIDs().size(); i++) {
            entry.users.add(userService.getUserById(b.getUserIDs().get(i)));
        }
        entry.creator = userService.getUserById(b.getCreator());
        return entry;
    }

    public Bill add(
            int id,
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
        bill.setCreator(id);
        return repo.save(bill);
    }

    public Optional<Bill> findBill(int id) {
        return repo.findById(id);
    }

    public void delete(Bill bill) {
        repo.delete(bill);
    }

}

