package com.bafl.webdriver.interaction.windows;

/**
 * selenium2WebDriverWithJava
 * Created by Bartek on 07.02.2016.
 */
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SwitchToWindowTest {
    public static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        driver = new FirefoxDriver();
        driver.navigate().to("http://www.compendiumdev.co.uk/selenium/frames");
    }
    @Test
    public void switchToNewWindow(){
        String framesWindowHandle = driver.getWindowHandle();
        System.out.println(framesWindowHandle);
        assertEquals("Expected only 1 current window", 1, driver.getWindowHandles().size());
        driver.switchTo().frame("content");
        driver.findElement(By.cssSelector("a[href='http://www.seleniumsimplified.com']")).click();
        assertEquals("Expected a New Window opened", 2, driver.getWindowHandles().size());
        Set<String> myWindows = driver.getWindowHandles();
        String newWindowHandle="";
        for(String aHandle : myWindows){
            if(!framesWindowHandle.contentEquals(aHandle)){
                newWindowHandle = aHandle;
                System.out.println(aHandle);
                break;
            }
        }
        driver.switchTo().window(newWindowHandle);
    assertTrue("Expected Selenium Simplified site",
               driver.getTitle().contains("Selenium Simplified"));

        driver.switchTo().window(framesWindowHandle);
        assertTrue("Expected main page title",driver.getTitle().startsWith("Frameset Example Title"));
}

    @Test
    public void switchBetweenWindowsByName(){
        String mainPageHandle = driver.getWindowHandle();
        System.out.println(mainPageHandle);
        driver.switchTo().frame(2);
        driver.findElement(By.id("goevil")).click();
        driver.findElement(By.cssSelector("a[target='compdev']")).click();
        Set<String> windowHandles = driver.getWindowHandles();
        System.out.println(windowHandles);
        driver.switchTo().window("evil");
        System.out.println(driver.getTitle());
        assertTrue("Expected page title contains EvilTester", driver.getTitle().contains("EvilTester.com"));
        driver.switchTo().window("compdev");
        System.out.println(driver.getTitle());
        assertTrue("Expected page title contains Software Testing", driver.getTitle().contains("Software Testing"));
        for (String aHandle : windowHandles) {
            driver.switchTo().window(aHandle);
            if (!aHandle.equals(mainPageHandle)) {
                driver.close();
            }
        }
        driver.switchTo().window(mainPageHandle);
        assertEquals("Should switch back to the main page",driver.getTitle(),"Frameset Example Title (Example 6)");
        assertThat(driver.getWindowHandles().size(), is(1));

    }

    @AfterClass
    public static void quit(){
        driver.close();
        driver.quit();
    }

}