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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.WebDriverWait.DEFAULT_SLEEP_TIMEOUT;

public class IntroductionToManipulation {
    private WebDriver driver;
    private String baseUrl;

    private Select category;

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://compendiumdev.co.uk/";
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testUntitled() throws Exception {
        driver.get(baseUrl + "/selenium/basic_ajax.html");
        category = new Select(driver.findElement(By.id("combo1")));
        System.out.println("Is category select multiple?: " + category.isMultiple());
        category.selectByIndex(1);
        category.selectByIndex(2);

        new WebDriverWait(driver, DEFAULT_SLEEP_TIMEOUT).until(waitForOptionAvailableInSelect(By.cssSelector("#combo2"), "Java"));

        new Select(driver.findElement(By.id("combo2"))).selectByVisibleText("Java");

        driver.findElement(By.name("submitbutton")).click();
        assertEquals("23", driver.findElement(By.id("_valuelanguage_id")).getText());
    }

    private ExpectedCondition<Boolean> waitForOptionAvailableInSelect(final By by, final String optionText) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    new Select(driver.findElement(by)).selectByVisibleText(optionText);
                    return true;
                } catch (NoSuchElementException e) {
                    return false;
                }
            }
        };
    }


    @After
        public void tearDown() {
            driver.quit();
        }

        }
