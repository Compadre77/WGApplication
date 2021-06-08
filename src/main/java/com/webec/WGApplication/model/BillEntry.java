package com.webec.WGApplication.model;

import com.webec.WGApplication.model.enums.BillStatus;
import com.webec.WGApplication.model.repository.UserRepository;
import com.webec.WGApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;

public class BillEntry {
    public int id;

    public Date date;
    public int creator;
    public String description;
    public double amount;
    public BillStatus billStatus;
    public List<Integer> userIDs;
    public List<UserEntry> users;
    public boolean isFix;

    public BillEntry(int id,
                     Date date,
                     int creator,
                     String description,
                     double amount,
                     BillStatus billStatus,
                     List<Integer> userIDs,
                     boolean isFix){
        this.id = id;
        this.date = date;
        this.creator = creator;
        this.description = description;
        this.amount = amount;
        this.billStatus = billStatus;
        this.userIDs = userIDs;
        this.isFix = isFix;
    }
}
