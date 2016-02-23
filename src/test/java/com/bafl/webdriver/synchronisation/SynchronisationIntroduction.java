package com.bafl.webdriver.synchronisation;

import com.bafl.webdriver.manager.DriverManager;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;

import static com.bafl.webdriver.navigation.NavigateToURL.URL;
import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

/**
 * selenium2WebDriverWithJava
 * Created by Bartek on 07.02.2016.
 */
public class SynchronisationIntroduction {
    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeClass
    public static void setUp(){
        driver = DriverManager.getDriver();
        driver.navigate().to(URL + "basic_ajax.html");
        wait = new WebDriverWait(driver,10,50);
    }

    @Test
    public void useWaitToAvoidTestFailed() throws NoSuchElementException{
        Select categorySelect,languageSelect;
        List<WebElement> selects = driver.findElements(By.tagName("select"));
        if (!(selects.size() > 0)){
            throw new NoSuchElementException("No such elements found");
        }
        categorySelect = new Select(selects.get(0));
        languageSelect = new Select(selects.get(1));

        //just playing around with some extra Java stuff
        Iterator<WebElement> optionsIterator = categorySelect.getOptions().iterator();
        while (optionsIterator.hasNext()) {
            System.out.println(optionsIterator.next().getText());
        }

        categorySelect.selectByVisibleText("Server");
        wait.until(visibilityOfElementLocated(By.xpath("//option[contains(.,'Cobol')]")));
        languageSelect.selectByVisibleText("Java");

        categorySelect.selectByVisibleText("Web");
        wait.until(elementToBeSelected(By.cssSelector("#combo2 > option[value='0']")));
        languageSelect.selectByVisibleText("Flash");

        categorySelect.selectByVisibleText("Desktop");
        wait.until(invisibilityOfElementLocated(By.cssSelector("#ajaxBusy")));
        languageSelect.selectByVisibleText("C");

        driver.findElement(By.name("submitbutton")).click();
        wait.until(titleIs("Processed Form Details"));

        assertEquals("Expected language id=12", Integer.parseInt(driver.findElement(By.xpath("//li[@id='_valuelanguage_id']")).getText()), 12);
    }
}
