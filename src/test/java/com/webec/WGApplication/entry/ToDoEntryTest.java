package com.webec.WGApplication.entry;

import com.webec.WGApplication.model.ToDoEntry;
import com.webec.WGApplication.model.UserEntry;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class ToDoEntryTest {

    int id = 1;
    String description = "test";
    int days = 1;
    int currentAssigneeId = 1;
    UserEntry userEntry = new UserEntry(1, "test", "test", null);
    Date currentDeadline = new Date(2012 - 3 - 14);
    List<Integer> userIDs = List.of(1);
    List<UserEntry> users = List.of(userEntry);

    @Test
    public void propertiesAreSetOnConstructor() {
        ToDoEntry testObject = new ToDoEntry(id, description, days, currentAssigneeId, currentDeadline, userIDs);

        Assert.assertEquals(id, testObject.id);
        Assert.assertEquals(description, testObject.description);
        Assert.assertEquals(days, testObject.days);
        Assert.assertEquals(currentAssigneeId, testObject.currentAssigneeId);
        Assert.assertEquals(currentDeadline, testObject.currentDeadline);
        Assert.assertEquals(userIDs, testObject.userIDs);
    }

}
