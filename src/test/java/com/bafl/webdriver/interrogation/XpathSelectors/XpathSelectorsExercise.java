package com.bafl.webdriver.interrogation.XpathSelectors;

import com.bafl.webdriver.navigation.NavigateToURL;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * selenium2WebDriverWithJava
 * Created by Bartek on 28.12.2015.
 */
public class XpathSelectorsExercise {
    private static WebDriver driver;
    private static String webpage;

    private WebElement pName31;
    private WebElement ulName1;
    private WebElement specialDiv;
    private WebElement li;

    private WebElement pName31CSS;
    private WebElement ulName1CSS;
    private WebElement specialDivCSS;
    private WebElement liCSS;

    private WebElement pName31Xpath;
    private WebElement ulName1Xpath;
    private WebElement specialDivXpath;
    private WebElement liXpath;



    @BeforeClass
    public static void setUp(){
        driver = new FirefoxDriver();
        webpage = "find_by_playground.php";
        driver.navigate().to(NavigateToURL.URL + webpage);
    }
    // Standard locators
    @Test
    public void assertP31Name() {
        pName31 = driver.findElement(By.id("p31"));
        assertEquals("expected name = 'pName31'","pName31",pName31.getAttribute("name"));
    }

    @Test
    public void assertUlName1Id() {
        ulName1 = driver.findElement(By.name("ulName1"));
        assertEquals("expected id = 'ul1'", "ul1", ulName1.getAttribute("id"));
    }


    @Test
    public void assertSpecialDivId() {
        specialDiv = driver.findElement(By.className("specialDiv"));
        assertEquals("expected id = 'div1'", "div1", specialDiv.getAttribute("id"));
    }


    @Test
    public void assertLiName() {
        li = driver.findElement(By.tagName("li"));
        assertEquals("expected name = 'liName1'", "liName1", li.getAttribute("name"));
    }

    // CSS locators
    @Test
    public void assertP31NameCssLocator() {
        pName31CSS = driver.findElement(By.cssSelector("#p31"));
        assertEquals("expected name = 'pName31'", "pName31", pName31CSS.getAttribute("name"));
    }


    @Test
    public void assertUlName1IdCssLocator() {
        ulName1CSS = driver.findElement(By.cssSelector("[name='ulName1']"));
        assertEquals("expected id = 'ul1'", "ul1", ulName1CSS.getAttribute("id"));
    }

    @Test
    public void assertSpecialDivIdCssLocator() {
        specialDivCSS = driver.findElement(By.cssSelector(".specialDiv"));
        assertEquals("expected id = 'div1'", "div1", specialDivCSS.getAttribute("id"));
    }

    @Test
    public void assertLiNameCssLocator() {
        liCSS= driver.findElement(By.cssSelector("li#li1.normal"));
        assertEquals("expected name = 'liName1'", "liName1", liCSS.getAttribute("name"));
    }

    // Xpath locators
    @Test
    public void assertP31NameXpathLocator() {
        pName31Xpath = driver.findElement(By.xpath("//p[@id='p31']"));
        assertEquals("expected name = 'pName31'", "pName31", pName31Xpath.getAttribute("name"));
    }

    @Test
    public void assertUlName1IdXpathLocator() {
        ulName1Xpath = driver.findElement(By.xpath("//ul[@name='ulName1']"));
        assertEquals("expected id = 'ul1'", "ul1", ulName1Xpath.getAttribute("id"));
    }

    @Test
    public void assertSpecialDivIdXpathLocator() {
        specialDivXpath = driver.findElement(By.xpath("//div[@class='specialDiv']"));
        assertEquals("expected id = 'div1'", "div1", specialDivXpath.getAttribute("id"));
    }

    @Test
    public void assertLiNameXpathLocator() {
        liXpath = driver.findElement(By.xpath("//ul/li[1]"));
        assertEquals("expected name = 'liName1'", "liName1", liXpath.getAttribute("name"));
    }


    // just a play
    @Test
    public void getAllLiElementAndPrintOutText() {
        List<WebElement> asd = driver.findElements(By.cssSelector(".linkDiv li a"));
        for (WebElement element : asd) {
            System.out.println(element.getText());
        }
    }
    @AfterClass
    public static void shutDown() {
        driver.quit();
    }
}
