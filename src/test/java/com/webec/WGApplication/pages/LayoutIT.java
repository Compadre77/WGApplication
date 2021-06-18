package com.webec.WGApplication.pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.web.server.LocalServerPort;

public class LayoutIT {

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
    void allNavigationLinksAreShown(){
        WebDriver driver = new HtmlUnitDriver();
        driver.navigate().to("http://localhost:" + port );

        var navLinks = driver.findElements(By.cssSelector("ul li"));
        Assert.assertEquals(5, navLinks.size());
    }

}
