package com.bafl.webdriver.manipulation;

/**
 * selenium2WebDriverWithJava
 * Created by Bartek on 07.01.2016.
 */
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class IntroductionToManipulation {
    private WebDriver driver;
    private String baseUrl;

    private Select category;

        @Before
        public void setUp() throws Exception {
            driver = new FirefoxDriver();
            baseUrl = "http://compendiumdev.co.uk/";
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }

        @Test
        public void testUntitled() throws Exception {
            driver.get(baseUrl + "/selenium/basic_ajax.html");
            category = new Select(driver.findElement(By.id("combo1")));
            System.out.println(category.isMultiple());
            category.selectByIndex(1);
            category.selectByIndex(2);
            new Select(driver.findElement(By.id("combo2"))).selectByVisibleText("Java");
            driver.findElement(By.name("submitbutton")).click();
            assertEquals("23", driver.findElement(By.id("_valuelanguage_id")).getText());
        }

        @After
        public void tearDown() {
            driver.quit();
        }

        }
