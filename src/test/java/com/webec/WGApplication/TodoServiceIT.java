package com.webec.WGApplication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webec.WGApplication.model.entity.ToDo;
import com.webec.WGApplication.model.repository.ToDoRepository;
import com.webec.WGApplication.service.ToDoService;
import com.webec.WGApplication.service.UserService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.IOException;
import java.util.List;

import static com.webec.WGApplication.SampleDataAdder.TODO_JSON_FILE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoServiceIT {

    @LocalServerPort
    int port;


    @Test
    public void checkbox(){
        var driver = new HtmlUnitDriver();
        driver.navigate().to("http://localhost:" + port);

        var checkbox = driver.findElements(By.cssSelector("input[type=checkbox]"));
        assertEquals(0, checkbox.size());
    }
}
