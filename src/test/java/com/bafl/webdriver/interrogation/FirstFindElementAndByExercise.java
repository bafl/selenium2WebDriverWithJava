package com.bafl.webdriver.interrogation;

import com.bafl.webdriver.navigation.NavigateToURL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * selenium2WebDriverWithJava
 * Created by Bartek on 28.12.2015.
 */
public class FirstFindElementAndByExercise {
    private WebDriver driver;
    private String webpage;
    private WebElement elementById;
    private WebElement elementByLinkText;
    private WebElement elementByName;
    private WebElement elementByPartialLinkText;
    private WebElement elementByClassName;


    @Before
    public void setUp(){
        driver = new FirefoxDriver();
    }

    @Test
    public void exploreFindByInDifferentWays(){
        webpage = "find_by_playground.php";
        driver.navigate().to(NavigateToURL.URL + webpage);

        //By.Id
        elementById = driver.findElement(By.id("p1"));
        assertThat(elementById.getText(),is("This is a paragraph text"));

        //By.LinkText
        elementByLinkText = driver.findElement(By.linkText("jump to para 24"));
        assertThat(elementByLinkText.getText(),is("jump to para 24"));

        //By.Name
        try {
            elementByName = driver.findElement(By.name("pNasme26"));
            fail("balbcxal");
        } catch (NoSuchElementException e){elementByName = driver.findElement(By.name("pName26"));};

        assertThat(elementByName.getAttribute("id"),is(equalTo("p26")));

        //By.PartialLinkText
        elementByPartialLinkText = driver.findElement(By.partialLinkText("para 1"));

        //By.ClassName
        elementByClassName = driver.findElement(By.className("multiple"));
        assertEquals("multiValues",elementByClassName.getAttribute("name"));
    }

    @After
    public void shutDown() {
        driver.quit();
    }
}
