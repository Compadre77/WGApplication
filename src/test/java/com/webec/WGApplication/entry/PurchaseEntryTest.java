package com.webec.WGApplication.entry;

import com.webec.WGApplication.model.PurchaseEntry;
import org.junit.Assert;
import org.junit.Test;

public class PurchaseEntryTest {

    int id = 1;
    int amount = 1;
    String description = "test";
    boolean checked = true;

    @Test
    public void propertiesAreSetOnConstructor(){

        PurchaseEntry testObject = new PurchaseEntry(id, amount, description, checked);

        Assert.assertEquals(id, testObject.id);
        Assert.assertEquals(amount, testObject.amount);
        Assert.assertEquals(description, testObject.description);
        Assert.assertTrue(testObject.checked);
    }

}
