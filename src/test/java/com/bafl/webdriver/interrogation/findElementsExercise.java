package com.bafl.webdriver.interrogation;

import com.bafl.webdriver.navigation.NavigateToURL;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.pagefactory.ByChained;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * selenium2WebDriverWithJava
 * Created by Bartek on 28.12.2015.
 */
public class findElementsExercise {

    private static WebDriver driver;
    private static String webpage;

    private WebElement chained;
    private List<WebElement> divs;
    private List<WebElement> aToPara;



    @BeforeClass
    public static void setUp(){
        driver = new FirefoxDriver();
        webpage = "find_by_playground.php";
        driver.navigate().to(NavigateToURL.URL + webpage);
    }

    @Test
    public void assertThatThereAre19Divs() {


        divs = driver.findElements(By.tagName("div"));
        assertThat(divs.size(), is(19));

        for (WebElement e : divs) {
            String elementID = e.getAttribute("id");
            String elementText = e.getText();
            System.out.println(elementID + ' ' + elementText);
        }

    }

    @Test
    public void assertThatThereAre25AWhichHrefToAPara(){
        aToPara = driver.findElements(By.partialLinkText("jump to para"));
        assertEquals("expected list size: 25",aToPara.size(),25);

    }

    @Test
    public void assertByChained(){
        chained = driver.findElement(
                new ByChained(
                        By.id("div2"),
                        By.name("nestedDiv"),
                        By.className("normal")));
    assertEquals("p27",chained.getAttribute("id"));
    }

    @AfterClass
    public static void shutDown() {
        driver.quit();
    }
}
