package com.bafl.webdriver.interrogation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * selenium2WebDriverWithJava
 * Created by Bartek on 28.12.2015.
 */
public class DriverInterrogationTest {

    private WebDriver driver;
    String expectedTitle = "Basic Web Page Title";
    String expectedURL = "http://compendiumdev.co.uk/selenium/basic_web_page.html";

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
    }

    @Test
    public void basicDriverIntrrogationTest() {
        driver.get("http://compendiumdev.co.uk/selenium/basic_web_page.html");
        assertEquals(driver.getTitle(), expectedTitle);
        System.out.println(driver.getCurrentUrl());
        assertEquals("expect same URL's", driver.getCurrentUrl(), expectedURL);
        assertTrue(driver.getPageSource().contains("A paragraph of text"));
        System.out.println(driver.getPageSource());

    }

    @After
    public void quit(){
        driver.close();
    }

}
