package com.webec.WGApplication.entry;

import com.webec.WGApplication.model.BillEntry;
import com.webec.WGApplication.model.enums.BillStatus;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class BillEntryTest {

    int id = 1;
    Date date = new Date(2021 -06 -18);
    int creatorId = 1;
    String description = "test";
    double amount = 1.0;
    BillStatus billStatus = BillStatus.POSITIV;
    List<Integer> userIDs = List.of(1);
    boolean isFix = true;

    @Test
    public void propertiesAreSetOnConstructor(){
        BillEntry testObject = new BillEntry(id, date, creatorId, description, amount, billStatus, userIDs, isFix);

        Assert.assertEquals(id, testObject.id);
        Assert.assertEquals(date, testObject.date);
        Assert.assertEquals(creatorId, testObject.creatorId);
        Assert.assertEquals(description, testObject.description);
        Assert.assertEquals(amount, testObject.amount);
        Assert.assertEquals(billStatus, testObject.billStatus);
        Assert.assertEquals(userIDs, testObject.userIDs);
        Assert.assertEquals(isFix, testObject.isFix);
    }
}

