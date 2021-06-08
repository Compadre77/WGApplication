package com.webec.WGApplication.model;

public class PurchaseEntry {
    public int id;
    int amount;
    public String description;
    public boolean checked;

    public PurchaseEntry(int id, int amount, String description, boolean checked){
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.checked = checked;
    }
}
