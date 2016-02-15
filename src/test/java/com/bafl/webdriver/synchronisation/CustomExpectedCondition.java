package com.bafl.webdriver.synchronisation;

/**
 * selenium2WebDriverWithJava
 * Created by Bartek on 15.02.2016.
 */

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Date;
import java.util.List;

import static com.bafl.webdriver.navigation.NavigateToURL.URL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class CustomExpectedCondition {
    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeClass
    public static void setUp(){
        driver = new FirefoxDriver();
        driver.navigate().to(URL + "basic_ajax.html");
        wait = new WebDriverWait(driver,10,50);
    }

    @Test
    public void useCustomInlineWaitForValuePresentInSelect(){

        final Select categorySelect,languageSelect;

        driver.navigate().to(URL + "basic_ajax.html");

        categorySelect = new Select(driver.findElement(By.id("combo1")));
        languageSelect = new Select(driver.findElement(By.id("combo2")));

        categorySelect.selectByVisibleText("Server");

        //Inline wait
        wait.until(new ExpectedCondition<WebElement>() {

            @Override
            public WebElement apply(WebDriver driver){
                return driver.findElement(By.cssSelector("option[value='23']"));
            }
        });

        List<WebElement> optionsList = languageSelect.getOptions();

        int optionPosition = 1;
        String message;
        for (WebElement webElement : optionsList){
            System.out.println(webElement.getText());
            if (webElement.getText().equals("Java")) {
                message = String.format("Java is on the %d position", optionPosition);
                fail(message);
            }
            optionPosition++;
        }

        languageSelect.selectByVisibleText("Java");

        driver.findElement(By.name("submitbutton")).click();
        wait.until(titleIs("Processed Form Details"));

        assertEquals("Expected language id=23", Integer.parseInt(driver.findElement(By.xpath("//li[@id='_valuelanguage_id']")).getText()), 23);
    }

    @Test
    public void createCustomExpectedCondition() {
        driver.navigate().to(URL + "basic_redirect.html");
        wait.until(titleIs("Basic Redirects"));
        driver.findElement(By.partialLinkText("5 seconds")).click();

        long startTime = new Date().getTime();
        String titleHasChangedAfter5Sec = null;
        try {
            titleHasChangedAfter5Sec = new WebDriverWait(driver,5,0).until(new TitleDoesNotContainText("Basic Redirects"));
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        long finishTime = new Date().getTime();
        long waitDuration = finishTime - startTime;
        System.out.println(waitDuration);
        assertEquals("Basic Web Page Title", titleHasChangedAfter5Sec);
    }

    private class TitleDoesNotContainText implements ExpectedCondition<String> {
        private String unwantedTitle;

        public TitleDoesNotContainText(String unwantedTitle) {
            this.unwantedTitle = unwantedTitle;
        }

        @Override
            public String apply (WebDriver input){
                String title =input.getTitle();
                if (title.contains(unwantedTitle)){
                    return null;
            } else {
                    return title;
                }
            }
        }
}
