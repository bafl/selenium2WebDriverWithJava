package com.bafl.webdriver.navigation;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * selenium2WebDriverWithJava
 * Created by Bartek on 21.12.2015.
 */
public class NavigateToURL {
    private static WebDriver driver;
    public static final String URL = "http://www.compendiumdev.co.uk/selenium/";

    static final String PROTOCOL = "http";
    static final String HOST = "www.compendiumdev.co.uk";
    static final String SEARCH_PAGE = "/selenium/search.php";

    @BeforeClass
    public static void setUp() {
        driver = new FirefoxDriver();
    }

    @Test
    public void navigateToDifferentURLs() throws MalformedURLException{

        java.net.URL searchPageURL = new URL(PROTOCOL,HOST,SEARCH_PAGE);

        driver.navigate().to(URL);
        assertTrue(driver.getTitle().equals("Selenium Simplified - a book and ebook on Automated Web Testing with Java and Selenium RC"));
        driver.navigate().to(searchPageURL);
        assertEquals("Check,that title match","Selenium Simplified Search Engine",driver.getTitle());
        driver.navigate().back();
        assertThat(driver.getTitle(),is(equalTo("Selenium Simplified - a book and ebook on Automated Web Testing with Java and Selenium RC")));
        driver.navigate().to(URL + "basic_html_form.html");
        assertEquals("HTML Form Elements", driver.getTitle());
        driver.navigate().to(URL + "basic_web_page.html");
        driver.navigate().back();
        driver.navigate().forward();
        assertThat(driver.getTitle(),is(equalTo("Basic Web Page Title")));
        driver.navigate().to(URL + "refresh.php");
        assertTrue(driver.getTitle().matches("Refreshed Page on ([0-9]{10})"));

    }

    @AfterClass
    public static void quit(){
        driver.close();
        driver.quit();
    }
}
