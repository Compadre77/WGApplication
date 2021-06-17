package com.webec.WGApplication.pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class IndexPageIT {

    @LocalServerPort
    int port;

    @BeforeAll
    public static void setupClass() {
        // This test requires Firefox to be installed! To use a different
        // Browser, change the pom file, the line below, and all references
        // to FirefoxDriver accordingly.
        WebDriverManager.firefoxdriver().setup();
    }

    @Test
    void allContentPresent() {
        WebDriver driver = new FirefoxDriver();
        driver.navigate().to("http://localhost:" + port);

        var usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("Sophia");

        var passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("sophia");

        var submitButton = driver.findElement(By.cssSelector(".btn.btn-lg.btn-primary.btn-block"));
        submitButton.click();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        var H1 = driver.findElements(By.cssSelector("h1"));
        assertEquals(1, H1.size());

        var nav = driver.findElements(By.cssSelector("nav"));
        assertEquals(1, nav.size());

        var todos = driver.findElements(By.id("todos"));
        assertEquals(1, todos.size());

        var finanzen = driver.findElements(By.id("finanzen"));
        assertEquals(1, finanzen.size());
    }

}
