package com.bafl.webdriver.manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.SessionNotFoundException;

/**
 * selenium2WebDriverWithJava
 * Created by Bartek on 22.02.2016.
 */
public class DriverManager {
    public static WebDriver aDriver = null;

    public static WebDriver getDriver() {
        if (aDriver == null) {
            aDriver = new FirefoxDriver();
        } else try {
            aDriver.getWindowHandle();
        } catch (SessionNotFoundException e) {
            aDriver = new FirefoxDriver();
        }
        return aDriver;
    }

    public static WebDriver getDriver(String url){
        getDriver();
        aDriver.navigate().to(url);
        return aDriver;
    }

}
