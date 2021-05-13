package com.webec.WGApplication.model.entity;

import com.webec.WGApplication.BillStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Entity
public class Bill {

    @Id
    @GeneratedValue
    private int id;

    private Date date;
    private String description;
    private double amount;
    private BillStatus billStatus;
    private List<User> users;

    private boolean isFix;
}
