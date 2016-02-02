package com.bafl.webdriver.interaction.frames;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.bafl.webdriver.navigation.NavigateToURL.URL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * selenium2WebDriverWithJava
 * Created by Bartek on 02.02.2016.
 */
public class FramesIntroduction {
    private static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        driver = new FirefoxDriver();
        driver.navigate().to(URL + "frames");
    }

    @Test
    public void assertPresenceOfElementAfterReturnFromOtherFrame(){
        assertEquals(driver.getTitle(),"Frameset Example Title (Example 6)");
        driver.switchTo().frame(2);
        WebElement greenPageLink;
        greenPageLink = driver.findElement(By.cssSelector("a[href=\"green.html\"]"));
        greenPageLink.click();
        new WebDriverWait(driver,10000).until(ExpectedConditions.visibilityOfElementLocated(By.id("green")));
        assertEquals(driver.findElement(By.tagName("h1")).getText(),"Green Page");
        driver.findElement(new ByChained(By.tagName("p"),By.tagName("a"))).click();
        assertTrue(driver.findElement(By.cssSelector("html>body>h1")).isDisplayed());
        assertEquals(driver.findElement(By.cssSelector("html>body>h1")).getText(),"Content");
    }

    @Test
    public void iFrameExercise(){
        assertEquals(driver.getTitle(),"Frameset Example Title (Example 6)");
        driver.switchTo().frame("menu");
        WebElement iFramesExampleLink;
        iFramesExampleLink = driver.findElement(By.cssSelector("body > a:last-of-type"));
        iFramesExampleLink.click();
        new WebDriverWait(driver,10000).until(ExpectedConditions.titleIs("HTML Frames Example - iFrame Contents"));
        driver.switchTo().frame(0);

    }

    @AfterClass
    public static void quit(){
        driver.close();
        driver.quit();
    }
}
