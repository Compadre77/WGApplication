package com.webec.WGApplication.controller;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

public class AboutControllerTest {

    AboutController controller = new AboutController();
    Model model = new ConcurrentModel();

    @Test
    public void testTodos(){
        Assert.assertEquals("about", controller.todos(model));
    }
}
