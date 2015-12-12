package com.bafl.webdriver;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


/**
 * selenium2WebDriverWithJava
 * Created by Bartek on 12.12.2015.
 */
public class MyFirstTest {
    @Test
    public void startWebDriver(){
        String expectedMainPageTitle = "Share Book Recommendations With Your Friends, Join Book Clubs, Answer Trivia";

        WebDriver driver = new FirefoxDriver();
        driver.navigate().to("https://www.goodreads.com/");
        Assert.assertTrue(driver.getTitle().equals(expectedMainPageTitle));
        driver.close();
        driver.quit();
    }
}
