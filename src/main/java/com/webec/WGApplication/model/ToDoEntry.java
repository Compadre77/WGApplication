package com.webec.WGApplication.model;

import java.util.Date;
import java.util.List;

public class ToDoEntry {
    public int id;
    public String description;
    public int days;
    public int currentAssigneeId;
    public UserEntry currentAssignee;
    public Date currentDeadline;
    public List<Integer> userIDs;
    public List<UserEntry> users;


    public ToDoEntry(
            int id,
            String description,
            int days,
            int currentAssigneeId,
            Date currentDeadline,
            List<Integer> userIDs
    ){
        this.id = id;
        this.description = description;
        this.days = days;
        this.currentAssigneeId = currentAssigneeId;
        this.currentDeadline = currentDeadline;
        this.userIDs = userIDs;
    }
}
