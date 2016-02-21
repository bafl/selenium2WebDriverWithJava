package com.bafl.webdriver.synchronisation;

/**
 * selenium2WebDriverWithJava
 * Created by Bartek on 19.02.2016.
 */

import com.google.common.base.Function;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static com.bafl.webdriver.navigation.NavigateToURL.URL;
import static org.junit.Assert.assertEquals;

public class FluentWaitExercise {
    static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        driver = new FirefoxDriver();
    }

    @Test
    public void useFluentWaitUntilTwoLastCharsAre04() {

        driver.navigate().to(URL + "javascript_countdown.html");

        final WebElement timeCounter = driver.findElement(By.cssSelector("#javascript_countdown_time"));

        WebElement wait = new FluentWait<WebElement>(timeCounter).
                pollingEvery(50, TimeUnit.MILLISECONDS).
                withMessage("This message will be displayed").
                withTimeout(8,TimeUnit.SECONDS).
                until(new Function<WebElement, WebElement>() {
                    @Override
                    public WebElement apply(WebElement input) {
                        System.out.println(input.getText());
                        if (input.getText().endsWith("04")) {
                            return input;
                        }
                        return null;
                    }
                });
        System.out.println(wait.getClass());
        ThreadLocal<WebDriver>
    }

    @Test
    public void useWebDriverWaitUntilTwoLastCharsAre04() {
        driver.navigate().to(URL + "javascript_countdown.html");
        String timeCounter = new WebDriverWait(driver,10).until(new ExpectedCondition<String>() {
            @Override
            public String apply(WebDriver input) {
                WebElement counter = driver.findElement(By.cssSelector("#javascript_countdown_time"));
                if (counter.getText().endsWith("04")) {
                    return counter.getText();
                } else {
                    return null;
                }
            }
        });
        assertEquals("01:01:04",timeCounter);
    }
}
