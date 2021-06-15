package com.webec.WGApplication.model.entity;

import com.webec.WGApplication.model.UserEntry;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Entity
public class ToDo {

    @Id
    @GeneratedValue
    private int id;

    private String description;
    private int days;
    private int currentAssignee;
    private Date currentDeadline;

    @ElementCollection
    private List<Integer> userIDs;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getCurrentAssignee() {
        return currentAssignee;
    }

    public void setCurrentAssignee(int currentAssigneeId) {
        this.currentAssignee = currentAssigneeId;
    }

    public Date getCurrentDeadline() { return currentDeadline; }

    public void setCurrentDeadline(Date currentDeadline) { this.currentDeadline = currentDeadline; }

    public List<Integer> getUserIDs() {
        return userIDs;
    }

    public void setUserIDs(List<Integer> userIDs) {
        this.userIDs = userIDs;
    }
}
