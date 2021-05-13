package com.webec.WGApplication.model;

public class PurchaseEntry {
    private int id;
    private String description;
    private boolean checked;

    public PurchaseEntry(int id, String description, boolean checked){
        this.id = id;
        this.description = description;
        this.checked = checked;
    }
}
