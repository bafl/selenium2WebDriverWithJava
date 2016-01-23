package com.bafl.webdriver.manipulation;

/**
 * selenium2WebDriverWithJava
 * Created by Bartek on 07.01.2016.
 */

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

public class ManipulationExercisesIntroduction {
    private static WebDriver driver;
    private static String baseUrl;
    private static String webpage;

    private WebElement submitButton;


    @BeforeClass
        public static void setUp() throws Exception {
            driver = new FirefoxDriver();
            baseUrl = "http://compendiumdev.co.uk/";
            webpage = "basic_html_form.html";
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }

        @Test
        public void submitFormAndAssertPageTitleChanges() throws Exception {
            driver.navigate().to(baseUrl + webpage);
            String pageTitle = driver.getTitle();
            clickSubmitButton(driver);
            assertTrue(pageTitle.compareTo(driver.getTitle()) != 0);
            assertThat(driver.getTitle(), is(not(pageTitle)));
        }

        @Test
        public void clearTypeCommentsSubmitFormAndCheckOutput(){
            driver.navigate().to(baseUrl + webpage);
            WebElement commentsTextarea = driver.findElement(By.tagName("textarea"));
            assertEquals("verify content of textarea", commentsTextarea.getText(),"Comments...");
            commentsTextarea.clear();
            commentsTextarea.sendKeys("Some text");
            clickSubmitButton(driver);
            WebElement comment = driver.findElement(By.cssSelector("li"));
            assertEquals(comment.getText(),"Some text");
        }

        @Test
        public void submitFormWithRadioTwoSelected(){
            driver.navigate().to(baseUrl + webpage);
            WebElement radioTwo = driver.findElement(By.cssSelector("input[value='rd2']"));
            if (!radioTwo.isSelected()){

                radioTwo.click();
            }
            clickSubmitButton(driver);
            assertThat(driver.findElement(By.cssSelector(" p[name='_radioval'] + ul  li")).getText(),is("rd2"));
        }

        @Test
        public void submitFormWithOnlyCheckboxOneSelected(){
            driver.navigate().to(baseUrl + webpage);
            WebElement checkboxOne;
            WebElement checkboxThree;
            checkboxOne = driver.findElement(By.cssSelector("input[value='cb1']"));
            checkboxThree = driver.findElement(By.cssSelector("input[value='cb3']"));
            if (!checkboxOne.isSelected()){
                checkboxOne.click();
            }
            if (checkboxThree.isSelected()){
                checkboxThree.click();
            }
            clickSubmitButton(driver);
            assertThat(driver.findElement(By.cssSelector(" p[name='_checkboxes'] + ul  li")).getText(),is("cb1"));
        }

        @Test
        public void submitFormWithDropDownItemFiveSelected(){
            driver.navigate().to(baseUrl + webpage);
            WebElement selectOption = driver.findElement(By.cssSelector("select[name='dropdown'] option[value='dd5']"));
            selectOption.click();
            clickSubmitButton(driver);
            assertThat(driver.findElement(By.cssSelector("#_dropdown li")).getText(),is("dd5"));
        }

        @Test
        public void submitFormWithMultipleSelectOneTwoAndThree(){
            driver.navigate().to(baseUrl + webpage);
            Select multipleSelect = new Select(driver.findElement(By.cssSelector("select")));
            if (multipleSelect.isMultiple()) {
                multipleSelect.deselectAll();
                multipleSelect.selectByValue("ms1");
                multipleSelect.selectByValue("ms2");
                multipleSelect.selectByValue("ms3");
            }

            clickSubmitButton(driver);

            List<WebElement> selectedOptionsList = driver.findElements(By.cssSelector("#_multipleselect li"));
            for (WebElement e : selectedOptionsList){
                System.out.println(e.getText());
            }


            String[] expectedOptionsArray ={"ms1","ms2","ms3"};
            List<String> expectedOptionsList = Arrays.asList(expectedOptionsArray);
            for (String s : expectedOptionsList){
                System.out.println(s);
            }

            assertEquals(selectedOptionsList.get(0).getText(), expectedOptionsList.get(0));

            List<String> selectedOptionsStringList = new ArrayList<String>();
            for (WebElement e : selectedOptionsList){
                selectedOptionsStringList.add(e.getText());
            }

            assertEquals(expectedOptionsList, selectedOptionsStringList);
        }

        @Test
        public void submitWithAFileAndCheckNameOnOutput(){
            driver.navigate().to(baseUrl + webpage);
            driver.findElement(By.name("filename")).sendKeys("C:\\Users\\Bartek\\SkyDrive\\ebooki\\javaForTesters.pdf");
            clickSubmitButton(driver);
//            driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']")).submit();
            assertThat(driver.findElement(By.id("_valuefilename")).getText(),is("javaForTesters.pdf"));
        }

    @AfterClass
        public static void tearDown() {
            driver.close();
            driver.quit();
        }

        public void clickSubmitButton(WebDriver driver){
            this.driver = driver;
            WebElement submitButton;
            submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
            submitButton.click();
        }

    }
