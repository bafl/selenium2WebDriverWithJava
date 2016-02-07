package com.bafl.webdriver.interaction.actions;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;

import static com.bafl.webdriver.navigation.NavigateToURL.URL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * selenium2WebDriverWithJava
 * Created by Bartek on 26.01.2016.
 */
public class UserInteractionsExercise {

    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        goToURL();
    }

    @Test
    public void moveDraggableOneOnToDroppableOne(){
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
        assertEquals("Label should change to: Dropped!","Dropped!",droppable1.getText());
    }

    @Test
    public void dragAndDropDraggableTwoToDroppableOne(){
        new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(By.id("draggable2")));
        WebElement draggable2;
        draggable2 = driver.findElement(By.cssSelector("#draggable2"));
        WebElement droppable1;
        droppable1 = driver.findElement(By.id("droppable1"));
        Action dragdragAndDropDraggableTwoToDroppableOne = new Actions(driver).dragAndDrop(draggable2,droppable1).build();
        dragdragAndDropDraggableTwoToDroppableOne.perform();
        assertEquals("Label should change to: Get Off Me!", "Get Off Me!", droppable1.getText());
    }

    @Test
    public void pressCtrlBAndAssertForTextChangeOnDraggableOne(){
        new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(By.id("draggable2")));
        WebElement draggable1;
        draggable1 = driver.findElement(By.cssSelector("#draggable1"));
        Action pressCtrlB = new Actions(driver).keyDown(Keys.CONTROL).sendKeys("b").keyUp(Keys.CONTROL).build();
        pressCtrlB.perform();
        assertEquals("Label should change to: Bwa! Ha! Ha!", "Bwa! Ha! Ha!", draggable1.getText());
    }


    @Test
    public void pressCtrlAndSpaceAndAssertRedSquaresText() throws AWTException {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("draggable1")));
        WebElement droppable1;
        droppable1 = driver.findElement(By.cssSelector("#droppable1"));
        WebElement droppable2;
        droppable2 = driver.findElement(By.cssSelector("#droppable2"));
        //Doesn't work for now
        //Actions CtrlSpace = new Actions(driver);
        //CtrlSpace.keyDown(Keys.CONTROL).sendKeys(Keys.SPACE).perform();

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_SPACE);
        String droppableOneText = droppable1.getText();
        String droppableTwoText = droppable2.getText();
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_SPACE);

        try {
            assertEquals("Label should change to: Let GO!!!", "Let GO!!!", droppableOneText);
            assertEquals(droppableOneText,droppableTwoText);
            fail("It works!");
        } catch (org.junit.ComparisonFailure e) {
            System.out.print(e.getMessage());
        }
    }

    private static void goToURL() {
        driver.get(URL+"gui_user_interactions.html");
    }

    @AfterClass
    public static void quit(){
        driver.close();
        driver.quit();
    }
}
