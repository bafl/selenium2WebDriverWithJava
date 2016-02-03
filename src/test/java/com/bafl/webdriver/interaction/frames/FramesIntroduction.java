package com.bafl.webdriver.interaction.frames;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.bafl.webdriver.navigation.NavigateToURL.URL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * selenium2WebDriverWithJava
 * Created by Bartek on 02.02.2016.
 */
public class FramesIntroduction {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeClass
    public static void setUp(){
        driver = new FirefoxDriver();
        driver.navigate().to(URL + "frames");
        wait = new WebDriverWait(driver,10000);
    }

    @Test
    public void assertPresenceOfElementAfterReturnFromOtherFrame(){
        assertEquals("Frameset Example Title (Example 6)",driver.getTitle());
        driver.switchTo().frame(2);
        WebElement greenPageLink;
        greenPageLink = driver.findElement(By.cssSelector("a[href=\"green.html\"]"));
        greenPageLink.click();
        wait.until(visibilityOfElementLocated(By.id("green")));
        assertEquals(driver.findElement(By.tagName("h1")).getText(),"Green Page");
        driver.findElement(new ByChained(By.tagName("p"),By.tagName("a"))).click();
        assertTrue(driver.findElement(By.cssSelector("html>body>h1")).isDisplayed());
        assertEquals("Content",driver.findElement(By.cssSelector("html>body>h1")).getText());
    }

    @Test
    public void iFrameExercise(){
        WebElement iFramesExampleLink;
        WebElement exampleFiveLink;
        WebElement loadMainFramesPageLink;

        assertEquals(driver.getTitle(),"Frameset Example Title (Example 6)");
        driver.switchTo().frame("menu");
        iFramesExampleLink = driver.findElement(By.cssSelector("body > a:last-of-type"));
        iFramesExampleLink.click();
        waitForTitle("HTML Frames Example - iFrame Contents");
        driver.switchTo().frame(0);
        exampleFiveLink = driver.findElement(By.linkText("Example 5"));
        exampleFiveLink.click();
        waitForTitle("Frameset Example Title (Example 5)");
        driver.switchTo().frame(2);
        loadMainFramesPageLink = driver.findElement(By.xpath("//ul[1]/li/a"));
        loadMainFramesPageLink.click();
        assertEquals("Expected title: Frameset Example Title (Example 6)","Frameset Example Title (Example 6)",driver.getTitle());
    }

    private void waitForTitle(String title) {
        wait.until(titleIs(title));
    }

    @AfterClass
    public static void quit(){
        driver.close();
        driver.quit();
    }
}
