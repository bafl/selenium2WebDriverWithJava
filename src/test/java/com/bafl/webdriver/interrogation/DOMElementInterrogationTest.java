package com.bafl.webdriver.interrogation;

import com.bafl.webdriver.navigation.NavigateToURL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * selenium2WebDriverWithJava
 * Created by Bartek on 28.12.2015.
 */
public class DOMElementInterrogationTest {
    private WebDriver driver;
    private WebElement para1;


    @Before
    public void setUp(){
        driver = new FirefoxDriver();
    }

    @Test
    public void navigateToURLAndGetTextFromElementBasedOnItsID(){
        driver.navigate().to(NavigateToURL.URL+"basic_web_page.html");
        para1 = driver.findElement(By.id("para1"));
        assertThat(para1.getText(),is("A paragraph of text"));
    }

    @After
    public void shutDown() {
        driver.quit();
    }
}
