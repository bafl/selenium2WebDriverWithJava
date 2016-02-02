package com.bafl.webdriver.interaction.alerts;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static com.bafl.webdriver.navigation.NavigateToURL.URL;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * selenium2WebDriverWithJava
 * Created by Bartek on 01.02.2016.
 */
public class AlertExercises {
    private static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        driver = new FirefoxDriver();
        driver.navigate().to(URL + "alerts.html");
    }

    @Test
    public void acceptAlert(){
        Alert alert = showAlert();
        assertTrue("Expected alert message: I am an alert box!",alert.getText().equals("I am an alert box!"));
        alert.accept();
    }

    @Test
    public void dismissAlert(){
        Alert alert = showAlert();
        alert.dismiss();
    }

    @Test
    public void acceptConfirm(){
        Alert confirm = showConfirm();
        assertEquals("Expected confirm message: 'I am a confirm alert'", "I am a confirm alert", confirm.getText());
        confirm.accept();
        Boolean confirmAccepted;
        confirmAccepted = new Boolean(driver.findElement(By.id("confirmreturn")).getText());
        assertTrue(confirmAccepted);
    }

    @Test
    public void dismissConfirm(){
        Alert confirm = showConfirm();
        assertEquals("Expected confirm message: 'I am a confirm alert'", "I am a confirm alert", confirm.getText());
        confirm.dismiss();
        Boolean confirmDismissed;
        confirmDismissed = new Boolean(driver.findElement(By.id("confirmreturn")).getText());
        assertFalse(confirmDismissed);
    }

    @Test
    public void acceptPrompt(){
        Alert prompt = showPrompt();
        assertEquals("Expected prompt message: 'I prompt you'", "I prompt you", prompt.getText());
        prompt.accept();
        WebElement promptTextBox;
        promptTextBox = driver.findElement(By.id("promptreturn"));
        assertThat(promptTextBox.getText(),is("change me"));
    }

    @Before
    public void refreshPage(){
        driver.navigate().refresh();
    }
    @Test
    public void dismissPrompt(){
        Alert prompt = showPrompt();
        assertEquals("Expected prompt message: 'I prompt you'", "I prompt you", prompt.getText());
        prompt.dismiss();
        WebElement promptTextBox;
        promptTextBox = driver.findElement(By.id("promptreturn"));
        assertThat(promptTextBox.getText(),is("pret"));
    }

    @Test
    public void changePromptTextAndAccept(){
        Alert prompt = showPrompt();
        assertEquals("Expected prompt message: 'I prompt you'", "I prompt you", prompt.getText());
        prompt.sendKeys("Some other text");
        prompt.accept();
        WebElement promptTextBox;
        promptTextBox = driver.findElement(By.id("promptreturn"));
        assertThat(promptTextBox.getText(),is("Some other text"));
    }

    @AfterClass
    public static void quit(){
        driver.close();
        driver.quit();
    }

    private Alert showAlert() {
        WebElement alertButton;
        alertButton = driver.findElement(By.id("alertexamples"));
        alertButton.click();
        return driver.switchTo().alert();
    }

    private Alert showConfirm(){
        WebElement confirm;
        confirm = driver.findElement(By.id("confirmexample"));
        confirm.click();
        return driver.switchTo().alert();
    }

    private Alert showPrompt(){
        WebElement prompt;
        prompt = driver.findElement(By.id("promptexample"));
        prompt.click();
        return driver.switchTo().alert();
    }
}
