package com.webec.WGApplication.model;

import java.util.List;

public class ToDoEntry {
    private int id;
    private String description;
    private int days;
    private int currentAssignee;
    private List<Integer> userIDs;

    public ToDoEntry(
            int id,
            String description,
            int days,
            int currentAssignee,
            List<Integer> userIDs
    ){
        this.id = id;
        this.description = description;
        this.days = days;
        this.currentAssignee = currentAssignee;
        this.userIDs = userIDs;
    }
}
