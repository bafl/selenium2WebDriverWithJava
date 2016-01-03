package com.bafl.webdriver.interrogation.CSSSelectors;

import com.bafl.webdriver.navigation.NavigateToURL;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.Assert.assertEquals;

/**
 * selenium2WebDriverWithJava
 * Created by Bartek on 28.12.2015.
 */
public class CSSSelectorsExercise {
    private static WebDriver driver;
    private static String webpage;

    private WebElement pName31;
    private WebElement pName31CSS;
    private WebElement ulName1;
    private WebElement ulName1CSS;
    private WebElement specialDiv;
    private WebElement specialDivCSS;
    private WebElement li;
    private WebElement liCSS;




    @BeforeClass
    public static void setUp(){
        driver = new HtmlUnitDriver();
        webpage = "find_by_playground.php";
        driver.navigate().to(NavigateToURL.URL + webpage);
    }

    @Test
    public void assertP31Name() {
        pName31 = driver.findElement(By.id("p31"));
        assertEquals("expected name = 'pName31'","pName31",pName31.getAttribute("name"));
    }

    @Test
    public void assertP31NameCssLocator() {
        pName31CSS = driver.findElement(By.cssSelector("#p31"));
        assertEquals("expected name = 'pName31'","pName31",pName31CSS.getAttribute("name"));
    }


    @Test
    public void assertUlName1Id() {
        ulName1 = driver.findElement(By.name("ulName1"));
        assertEquals("expected id = 'ul1'", "ul1", ulName1.getAttribute("id"));
    }

    @Test
    public void assertUlName1IdCssLocator() {
        ulName1CSS = driver.findElement(By.cssSelector("[name='ulName1']"));
        assertEquals("expected id = 'ul1'", "ul1", ulName1CSS.getAttribute("id"));
    }


    @Test
    public void assertSpecialDivId() {
        specialDiv = driver.findElement(By.className("specialDiv"));
        assertEquals("expected id = 'div1'", "div1", specialDiv.getAttribute("id"));
    }

    @Test
    public void assertSpecialDivIdCssLocator() {
        specialDivCSS = driver.findElement(By.cssSelector(".specialDiv"));
        assertEquals("expected id = 'div1'", "div1", specialDivCSS.getAttribute("id"));
    }

    @Test
    public void assertLiName() {
        li = driver.findElement(By.tagName("li"));
        assertEquals("expected name = 'liName1'", "liName1", li.getAttribute("name"));
    }

    @Test
    public void assertLiNameCssLocator() {
        liCSS = driver.findElement(By.cssSelector("li#li1.normal"));
        assertEquals("expected name = 'liName1'", "liName1", liCSS.getAttribute("name"));
    }


    @AfterClass
    public static void shutDown() {
        driver.quit();
    }
}
