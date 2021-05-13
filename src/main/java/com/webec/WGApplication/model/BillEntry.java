package com.webec.WGApplication.model;

import com.webec.WGApplication.model.enums.BillStatus;

import java.util.Date;
import java.util.List;

public class BillEntry {
    private int id;

    private Date date;
    private int creator;
    private String description;
    private double amount;
    private BillStatus billStatus;
    private List<Integer> userIDs;
    private boolean isFix;

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
