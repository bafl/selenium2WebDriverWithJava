package com.bafl.webdriver.interaction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.bafl.webdriver.navigation.NavigateToURL.URL;
import static org.junit.Assert.assertEquals;

/**
 * selenium2WebDriverWithJava
 * Created by Bartek on 26.01.2016.
 */
public class UserInteractionsExercise {

    private WebDriver driver;

    @Before
    public void setUp(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

    @Test
    public void moveDraggableOneOnToDroppableOne(){
        driver.get(URL+"gui_user_interactions.html");
        new WebDriverWait(driver,5).until(ExpectedConditions.titleIs("GUI User Interactions"));
        WebElement draggable1;
        draggable1 = driver.findElement(By.cssSelector("#draggable1"));
        WebElement droppable1;
        droppable1 = driver.findElement(By.id("droppable1"));
        Action moveDraggableOneOnToDroppableOne;
        moveDraggableOneOnToDroppableOne = new Actions(driver).
                                            clickAndHold(draggable1).
                                            release(droppable1).
                                            build();
        moveDraggableOneOnToDroppableOne.perform();
        assertEquals("Label should changed to: Dropped!","Dropped!",droppable1.getText());
    }

    @After
    public void quit(){
        driver.close();
        driver.quit();
    }
}
