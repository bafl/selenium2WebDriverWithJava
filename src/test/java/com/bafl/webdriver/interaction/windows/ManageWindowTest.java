package com.bafl.webdriver.interaction.windows;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static com.bafl.webdriver.navigation.NavigateToURL.URL;
import static org.openqa.selenium.WebDriver.Window;

/**
 * selenium2WebDriverWithJava
 * Created by Bartek on 07.02.2016.
 */
public class ManageWindowTest {
    private static WebDriver driver;
    private static Window currentWindow;
    private static Point desiredPosition;

    @BeforeClass
    public static void setUp(){
        driver = new FirefoxDriver();
        driver.get(URL + "bounce.html");
        currentWindow = driver.manage().window();
    }

    @Test
    public void windowManageExercise(){
        currentWindow.setPosition(new Point(0,0));
        currentWindow.maximize();
        Dimension windowSize= getCurrentSize();
        System.out.println(windowSize );
        int maximizedWindowWidth = getCurrentWidth();
        int maximizedWindowHeight = getCurrentHeight();
        currentWindow.setSize(new Dimension(maximizedWindowWidth /2, maximizedWindowHeight /2));
        System.out.println(getCurrentSize());
        int centerXPoint = (maximizedWindowWidth/2) - (getCurrentWidth()/2);
        int centerYPoint = (maximizedWindowHeight/2) - (getCurrentHeight()/2);
        desiredPosition = new Point(centerXPoint,centerYPoint);
        //fuckin perfect :)
        currentWindow.setPosition(desiredPosition);
    }

    private Dimension getCurrentSize() {
        return currentWindow.getSize();
    }

    private int getCurrentHeight() {
        return getCurrentSize().getHeight();
    }

    private int getCurrentWidth() {
        return getCurrentSize().getWidth();
    }


}
