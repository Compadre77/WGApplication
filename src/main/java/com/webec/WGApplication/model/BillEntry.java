package com.webec.WGApplication.model;

import com.webec.WGApplication.model.enums.BillStatus;

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
