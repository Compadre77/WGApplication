package com.webec.WGApplication.model;

import java.util.Date;
import java.util.List;

public class ToDoEntry {
    private int id;
    private String description;
    private int days;
    private int currentAssignee;
    private Date currentDeadline;
    private List<Integer> userIDs;

    public ToDoEntry(
            int id,
            String description,
            int days,
            int currentAssignee,
            Date currentDeadline,
            List<Integer> userIDs
    ){
        this.id = id;
        this.description = description;
        this.days = days;
        this.currentAssignee = currentAssignee;
        this.currentDeadline = currentDeadline;
        this.userIDs = userIDs;
    }
}
