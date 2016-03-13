package com.bafl.webdriver.javascript;

import com.bafl.webdriver.manager.DriverManager;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * selenium2WebDriverWithJava
 * Created by Bartek on 13.03.2016.
 */
public class JavaScriptExecution {
    private WebDriver driver;
    private JavascriptExecutor jsExecutor;

    @Before
    public void setUp(){
        driver = DriverManager.getDriver("http://compendiumdev.co.uk/selenium/canvas_basic.html");
    }

    @Test
    public void callTheDrawFunction(){
        driver.findElement(By.cssSelector("input[value='Clear']")).click();
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("draw(1,arguments[0],30,54,'#FF0000');",0);
    }

    @Test
    public void executeAScriptThatAddsTwoArguments(){
        jsExecutor = (JavascriptExecutor) driver;
        System.out.println(jsExecutor.executeScript("return 15.5+2.7;"));
        jsExecutor.executeScript("document.getElementsByID(\"xnum\")[0].setAttribute(\"readonly\", \"readonly\");");
    }

    @Test
    public void setReadonlyAttributeToInputFieldAndTryToSendKeys(){
        jsExecutor = (JavascriptExecutor) driver;
        String elementID = "xnum";
        WebElement inputField = driver.findElement(By.id(elementID));
        inputField.clear();
        jsExecutor.executeScript("document.getElementById(arguments[0]).setAttribute('readonly', 'readonly');",elementID);
        typeText(inputField,"Not able to send keys");
//        assertEquals("Input field should be empty", 0, inputField.getText().length());
        jsExecutor.executeScript("document.getElementById(arguments[0]).removeAttribute('readonly');", elementID);
        typeText(inputField,"Able to send keys");
    }

    private void typeText(WebElement webElement, String text){
        webElement.sendKeys(text);
    }
}
