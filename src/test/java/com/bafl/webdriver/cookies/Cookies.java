package com.bafl.webdriver.cookies;

import com.bafl.webdriver.manager.DriverManager;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;

/**
 * selenium2WebDriverWithJava
 * Created by Bartek on 03.03.2016.
 */
public class Cookies {
    private final String VISIT_NUMBER_COOKIE = "seleniumSimplifiedSearchNumVisits";
    private static WebDriver driver;

    private WebElement searchInput;
    private WebElement searchButton;

    @BeforeClass
    public static void setUp(){
        driver = DriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(2, SECONDS);
    }

    @Before
    public void prepareWebPage() {
        driver.navigate().to("http://compendiumdev.co.uk/selenium/search.php");
        driver.manage().deleteAllCookies();
        refreshWebElements();
    }

    @Test
    public void searchAndCheckNumberOfVisitsEquals1() {
        search("WebDriver");
        Cookie visitNumber = driver.manage().getCookieNamed(VISIT_NUMBER_COOKIE);
        assertEquals("First visit expected", "1", visitNumber.getValue());
    }

    @Test
    public void setVisitsCookieTo42AndCheckNextSearchIs43ByCloningACookie(){
        driver.navigate().refresh();
        refreshWebElements();
        Cookie storedCookie = driver.manage().getCookieNamed(VISIT_NUMBER_COOKIE);
        driver.manage().deleteAllCookies();
        Cookie modifiedCookie = new Cookie(storedCookie.getName(),"42",storedCookie.getDomain(),storedCookie.getPath(),storedCookie.getExpiry(),storedCookie.isSecure(),storedCookie.isSecure());
        driver.manage().addCookie(modifiedCookie);
        search("Selenium");
        assertEquals("Next visit value expected to be: 43",String.valueOf(43),driver.manage().getCookieNamed(VISIT_NUMBER_COOKIE).getValue());
    }

    @Test
    public void setVisitsCookieTo42AndCheckNextSearchIs43ByCreatingCookieUsingConstructor(){
        //Set of name, value and path must match the cookie created by webpage in order to avoid duplicate Cookie with the same name
        Cookie newVisitCookie = new Cookie(VISIT_NUMBER_COOKIE,"42","/selenium/");
        driver.manage().addCookie(newVisitCookie);
        search("Selenium");
        assertEquals("Next visit value expected to be: 43",String.valueOf(43),driver.manage().getCookieNamed(VISIT_NUMBER_COOKIE).getValue());
    }

    @Test
    public void setVisitsCookieTo42AndCheckNextSearchIs43ByCreatingCookieUsingBuilder(){
        Cookie.Builder newVisitCookieBuilder = new Cookie.Builder(VISIT_NUMBER_COOKIE,"42");
        newVisitCookieBuilder.domain("compendiumdev.co.uk");
        newVisitCookieBuilder.path("/selenium/");
        driver.manage().addCookie(newVisitCookieBuilder.build());
        search("Something else");
        assertEquals("Next visit value expected to be: 43",String.valueOf(43),driver.manage().getCookieNamed(VISIT_NUMBER_COOKIE).getValue());
    }

    @AfterClass
    public static void quit(){
        driver.close();
    }

    public void refreshWebElements(){
        searchInput = driver.findElement(By.name("q"));
        searchButton = driver.findElement(By.name("btnG"));
    }

    public void search(String textToSearchFor){
        refreshWebElements();
        searchInput.clear();
        searchInput.sendKeys(textToSearchFor);
        searchButton.click();
    }
}
