package com.bafl.webdriver.synchronisation;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Iterator;
import java.util.List;

import static com.bafl.webdriver.navigation.NavigateToURL.URL;

/**
 * selenium2WebDriverWithJava
 * Created by Bartek on 07.02.2016.
 */
public class SynchronisationIntroduction {
    static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        driver = new FirefoxDriver();
        driver.navigate().to(URL + "basic_ajax.html");
    }

    @Test
    public void useWaitToAvoidTestFailed() throws NoSuchElementException{
        Select categorySelect,languageSelect;
        List<WebElement> selects = driver.findElements(By.tagName("select"));
        if (!(selects.size() > 0)){
            throw new NoSuchElementException("No such elements found");
        }
        categorySelect = new Select(selects.get(0));

        //just playing around with some extra Java stuff
        Iterator<WebElement> optionsIterator = categorySelect.getOptions().iterator();
        while (optionsIterator.hasNext()) {
            System.out.println(optionsIterator.next().getText());
        }

        categorySelect.selectByVisibleText("Server");
        languageSelect = new Select(selects.get(1));
        languageSelect.selectByVisibleText("Java");


    }
}
