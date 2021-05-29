package com.webec.WGApplication.model;

public class PurchaseEntry {
    public int id;
    public String description;
    public boolean checked;

    public PurchaseEntry(int id, String description, boolean checked){
        this.id = id;
        this.description = description;
        this.checked = checked;
    }
}
