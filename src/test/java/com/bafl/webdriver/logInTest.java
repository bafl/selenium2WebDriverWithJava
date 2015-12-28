package com.bafl.webdriver;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * selenium2WebDriverWithJava
 * Created by Bartek on 12.12.2015.
 */
public class logInTest {
    private static WebDriver driver;
    private static String baseUrl;
    private static StringBuffer verificationErrors = new StringBuffer();
    private static WebElement emailInput;
    private String expectedTitle = "Share Book Recommendations With Your Friends, Join Book Clubs, Answer Trivia";

    @BeforeClass
    public static void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "https://www.goodreads.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void tryToLogInWithEmptyCredentials() throws Exception {
        driver.navigate().to(baseUrl);
        assertThat(driver.getTitle(), is(equalTo(expectedTitle)));
        driver.findElement(By.cssSelector("input.button")).click();
        assertThat(driver.getTitle(), is("Sign Up"));
        try {
            assertEquals("Sorry, you must enter a name to sign up for Goodreads.", driver.findElement(By.cssSelector("p.flash.error")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        driver.navigate().back();

    }

    @Test
    public void tryToLogInUsingIncorrectEmail() throws Exception {
        emailInput = driver.findElement(By.id("user_email"));

        emailInput.clear();
        assertThat(emailInput.getAttribute("placeholder"),is("you@yours.com"));
        emailInput.sendKeys("asd");
        driver.findElement(By.name("next")).click();


        //driver.findElement(By.cssSelector("input.button")).click();
        //assertThat(driver.getTitle(), is("Sign in"));
        //try {
        //    assertEquals("Sorry, we didn’t recognize that email/password combination. Please try again.", driver.findElement(By.cssSelector("p.flash.error")).getText());
        //} catch (Error e) {
        //    verificationErrors.append(e.toString());
        //}
    }

    @AfterClass
    public static void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}
