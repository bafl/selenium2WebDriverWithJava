package com.bafl.webdriver.navigation;


import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertTrue;


/**
 * selenium2WebDriverWithJava
 * Created by Bartek on 12.12.2015.
 */
public class MyFirstTest {

    private String url;
    String expectedMainPageTitle = "Selenium Simplified - a book and ebook on Automated Web Testing with Java and Selenium RC";

    @Before
    public void setUp() throws Exception {
        url = "http://www.compendiumdev.co.uk/selenium";
    }

    @Test
    public void startWebDriver(){

        WebDriver driver = new FirefoxDriver();
        driver.navigate().to(url);
        assertTrue(driver.getTitle().equals(expectedMainPageTitle));
        driver.close();
        driver.quit();
    }
}
