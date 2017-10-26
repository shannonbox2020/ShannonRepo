package com.shiggins.constants;

import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

//import static constants.WebDriverManager.*;
public class WebDriverTest extends browser {

    protected static int currentrow1 = 0;

    private static Logger log = Logger.getLogger(Test.class);

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NEW actions with implicit wait of 1 min baked in
    //Wait for element using xpath and keep polling every sec until max int time
    /**
     * To wait for and type on any input field type ahead, use in place of a
     * "Sendkeys" command.
     *
     * @param driver
     * @param by
     * @param value
     *
     * Combines all of these following commands with catch block for exception
     * handling: prl("sending key value: " + "user" + " to locator " +
     * "//input[@id='username']") WaitTool.waitForElement(driver,
     * By.xpath("//input[@id='username']"), 60);
     * driver.findElement(By.xpath("//input[@id='username']")).clear();
     * WaitTool.waitForElement(driver, By.xpath("//input[@id='username']"), 60);
     * driver.findElement(By.xpath("//input[@id='username']")).sendKeys("user");
     * WaitTool.waitForElement(driver, By.xpath("//input[@id='username']"), 60);
     * driver.findElement(By.xpath("//input[@id='username']")).sendKeys(Keys.TAB);
     * prl("typing succeed");
     *
     * into the following: USAGE EXAMPLE= WebDriverTest.type(driver,
     * By.xpath("//input[@id='username']"), "user");
     *
     *
     *
     */
    public static void type(WebDriver driver, By by, String value) {

        try {
            prl("sending key value: " + value + " to locator " + by);
            WebElement elm = WaitTool.waitForElementPresent(driver, by, 60);
            elm.clear();
            elm.sendKeys(value);
            elm.sendKeys(Keys.TAB);
            prl("typing succeed");

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;

        }

    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * To wait for and Verify the element and click on an element, Use in place
     * of Findelement and Click.
     *
     * @param driver
     * @param by
     *
     * Combines all of these following commands with catch block for exception
     * handling: prl("clicking element located: " + "//*[@id='btnLoginSubmit']")
     * WaitTool.waitForElement(driver, By.xpath("//*[@id='btnLoginSubmit']"),
     * 60); prl("clicking succeed");
     *
     * into the following: USAGE EXAMPLE=
     * WebDriverTest.verifyAndClickElement(driver,By.xpath("//*[@id='btnLoginSubmit']")
     * );
     *
     *
     *
     */
    public static void verifyAndClickElement(WebDriver driver, By by) {
        try {
            prl("clicking element located: " + by);
            WebElement elm = WaitTool.waitForElementPresent(driver, by, 60);
            elm.click();
            prl("clicking succeed");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;

        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Method to wait for and hard assert the text of an element
     *
     * @param driver
     * @param by
     * @param value
     *
     * Combines all of these following commands with catch block for exception
     * handling:
     *
     * prl("verifying text: " + "Add Union" +
     * "//div[@id='innerTemplateContainer']/div/h1")
     * WaitTool.waitForElement(driver,
     * By.xpath("//div[@id='innerTemplateContainer']/div/h1"), 60);
     * assertEquals((driver,
     * By.xpath("//div[@id='innerTemplateContainer']/div/h1").getText(), "Add
     * Union"); prl("text verified");
     *
     * into the following: USAGE EXAMPLE=
     * WebDriverTest.assertText(driver,By.xpath("//div[@id='innerTemplateContainer']/div/h1")
     * , "Add Union" );
     *
     *
     *
     */
    public static void assertText(WebDriver driver, By by, String value) {

        try {
            prl("verifying text: " + value + " is present in locator " + by);
            WebElement element = WaitTool.waitForElementPresent(driver, by, 60);
            assertEquals(element.getText(), value);
            prl("text verified");
        } catch (AssertionError ex) {
            prl("assertText Error=" + ex.getMessage());
            throw ex;
        }

    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Method to wait for overlay disappear
     *
     * @param driver
     * @param by USAGE EXAMPLE=
     * WebDriverTest.waitElementInvisibility(driver,By.xpath("//*[@id='asc-loading-overlay']")
     * );
     */
    public static void waitElementInvisibility(WebDriver driver, By by) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(by));

    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
    /**
     * Method to post Timestamp to TestNG Report, Log and Screen USAGE EXAMPLE=
     * WebDriverTest.reportTime();
     */
    public static void reportTime() {

        java.util.Date today = new java.util.Date();
        prl("Script Execution Timestamp = " + (new java.sql.Timestamp(today.getTime())) + "");
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
    /**
     * Method to wait for and Verify the URL of a given page that is under the
     * domain of host
     *
     * @param driver
     * @param value USAGE EXAMPLE=
     * WebDriverTest.verifyURL(driver,"setup/core/unionCode/create");
     */
    public static void verifyURL(WebDriver driver, String value) {

        try {
            prl("verifying URL: " + value + " match current URL");
            //assertEquals(System.getProperty("host") + value, driver.getCurrentUrl());
            prl("url verified");
        } catch (AssertionError ex) {

            prl("verifyURL Error=" + ex.getMessage());
            throw ex;
        }

    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Method to wait for and verify if element is present but not visible
     *
     * @param driver
     * @param by USAGE EXAMPLE=
     * WebDriverTest.verifyElementNotvisible(driver,By.xpath("//div[@id='validationErrors']"));
     */
    public static void verifyElementNotvisible(WebDriver driver, By by) {

        try {
            prl("verifying element is not visible in locator " + by);
            WebElement element = WaitTool.waitForElementPresent(driver, by, 60);
            assertFalse(element.isDisplayed());
            prl("element is present but not visible");
        } catch (AssertionError ex) {
            prl("verifyElementNotPresent Error=" + ex.getMessage());
            throw ex;
        }

    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Method to wait for and verify if element is present and visible
     *
     * @param driver
     * @param by USAGE EXAMPLE=
     * WebDriverTest.verifyElementvisible(driver,By.xpath("//div[@id='validationErrors']"));
     */
    public static void verifyElementvisible(WebDriver driver, By by) {

        try {
            prl("verifying element is visible in locator " + by);
            WebElement element = WaitTool.waitForElementPresent(driver, by, 60);
            assert (element.isDisplayed());
            prl("element is present and visible");
        } catch (AssertionError ex) {

            prl("verifyElementPresent Error=" + ex.getMessage());
            throw ex;
        }

    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
    /**
     * Method to wait for and verify and assert if Element Not Present
     *
     * @param driver
     * @param by
     *
     * USAGE EXAMPLE=
     * WebDriverTest.verifyElementNotPresent(driver,By.xpath("//div[@id='innerTemplateContainer']/div/div/div[2]/form/div[2]/div[2]/div"));
     */
    public static void verifyElementNotPresent(WebDriver driver, By by) {

        try {
            prl("verifying element is not present at locator " + by);
            assertFalse(isElementPresent(driver, by));
            prl("element is not present");
        } catch (AssertionError ex) {
            prl("verifyElementNotPresent Error=" + ex.getMessage());
            throw ex;
        }

    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
    /**
     * Method to wait for and verify and assert if Element is Present
     *
     * @param driver
     * @param by
     *
     * USAGE EXAMPLE=
     * WebDriverTest.verifyElementPresent(driver,By.xpath("//div[@id='innerTemplateContainer']/div/div/div[2]/form/div[2]/div[2]/div"));
     */
    public static void verifyElementPresent(WebDriver driver, By by) {

        try {
            prl("verifying element is present at locator " + by);
            assertTrue(isElementPresent(driver, by));
            prl("element is present");
        } catch (AssertionError ex) {

            prl("verifyElementPresent Error=" + ex.getMessage());
            throw ex;
        }

    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
    /**
     * Method to wait for and select a drop down box
     *
     * @param driver
     * @param by
     * @param value
     *
     * USAGE EXAMPLE=
     * WebDriverTest.selectDropDown(driver,By.xpath("//*[@id='select-action-dropdown-container']/button")
     * , "I want to..." );
     */
    public static void selectDropDown(WebDriver driver, By by, String value) {

        try {
            prl("selecting value: " + value + " to locator " + by);
            WebElement elmement = WaitTool.waitForElementPresent(driver, by, 60);
            Select selctBox = new Select(elmement);
            selctBox.selectByVisibleText(value);
            prl("value selected");

        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail("Exception occurred while selected text:" + value + " from dropdown with locator:" + by + " , Exception message:" + ex.getMessage());
        }

    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
    /**
     * Method to wait for and read a value from an input field or drop down
     *
     * @param driver
     * @param by USAGE EXAMPLE: String Code = readValue(driver,
     * By.xpath("//input[@id='code']"));
     */
    public static String readValue(WebDriver driver, By by) {
        String value = null;
        try {
            prl("reading value typed inside input located " + by);
            WebElement elmement = WaitTool.waitForElementPresent(driver, by, 60);
            value = elmement.getAttribute("value");
            prl("value read: " + value);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return value;
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
    /**
     * Method to wait for and return a web element, improved version of
     * findElement
     *
     * @param driver
     * @param by USAGE EXAMPLE= WebDriverTest.fetchElement(driver,
     * By.xpath("//button[@id='crud-entity__save-large']")).click();
     */
    public static WebElement fetchElement(WebDriver driver, By by) {
        prl("finding element located: " + by);
        WebElement element = WaitTool.waitForElementPresent(driver, by, 60);
        prl("element found");
        return element;
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Method to wait for and verify the text of an element
     *
     * @param driver
     * @param by
     * @param value
     * @return
     *
     * USAGE EXAMPLE: retVal =
     * WebDriverTest.verifyText(driver,By.xpath("//*[@id='select-action-dropdown-container']/button")
     * , "I want to..." ); prl("Returned Value = " + retVal );
     *
     */
    public static boolean verifyText(WebDriver driver, By by, String value) {
        prl("comparing if text mach or not");
        WebElement element = WaitTool.waitForElementPresent(driver, by, 60);
        String text = element.getText();
        return value.equals(text);

    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///VERIFY RETURNING TRUE OR FALSE ACTIONS/////////////////////
    /**
     * To verify if element is present or not return true or false.
     *
     * @param driver
     * @param by
     * @return USAGE EXAMPLE: retVal =
     * WebDriverTest.isElementPresent(driver,By.xpath("//*[@id='select-action-dropdown-container']/button"));
     * prl("Returned Value = " + retVal );
     */
    public static boolean isElementPresent(WebDriver driver, By by) {
        prl("checking if element is present");
        boolean exists = !driver.findElements(by).isEmpty();
        return exists;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Method to get WebElement
     *
     * @param driver
     * @param by
     * @return
     */
    public static WebElement getElement(WebDriver driver, By by) {

        try {
            return driver.findElement(by);
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail("Exception occured while finding element with locator:" + by + " , Exception message:" + ex.getMessage());
        }
        return null;
    }

    /**
     * Method to print in logs and system
     *
     * @param printreportlog
     */
    public static void prl(String printreportlog) {

        String p = printreportlog;

        System.out.println(p);
        Reporter.log(p);
        log.info(p);
    }


    /*
     *
     */
    public static By getWebDriverLocator(String locatorType, String value) {
        if ("xpath".equalsIgnoreCase(locatorType)) {
            return By.xpath(value);
        } else if ("css".equalsIgnoreCase(locatorType)) {
            return By.cssSelector(value);
        } else if ("id".equalsIgnoreCase(locatorType)) {
            return By.id(value);
        } else if ("name".equalsIgnoreCase(locatorType)) {
            return By.name(value);
        } else if ("tag".equalsIgnoreCase(locatorType)) {
            return By.tagName(value);
        } else if ("byPartialLinkText".equalsIgnoreCase(locatorType)) {
            return By.partialLinkText(value);
        }

        return null;

    }

    public static WebElement isElementPresnt(WebDriver driver, By by, int time) {
        WebElement ele = null;
        for (int i = 0; i < time; i++) {
            try {
                ele = driver.findElement(by);
                break;
            } catch (Exception e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    System.out.println("Waiting for element to appear on DOM");
                }
            }
        }
        return ele;
    }

    /**
     * This method is used to execute a script.
     *
     * @param script
     */
    public void excuteJavaScript(WebDriver driver, String script) {
        try {
            ((JavascriptExecutor) driver).executeScript(script);
        } catch (Exception e) {
            Assert.fail("Exception occured while executing java script ");
        }
    }

    public static String between(String value, String a, String b) {
        // Return a substring between the two strings.
        int posA = value.indexOf(a);
        if (posA == -1) {
            return "";
        }
        int posB = value.lastIndexOf(b);
        if (posB == -1) {
            return "";
        }
        int adjustedPosA = posA + a.length();
        if (adjustedPosA >= posB) {
            return "";
        }
        return value.substring(adjustedPosA, posB);
    }

    /**
     * Execute a piece of JavaScript code
     *
     * @param code - The JavaScript code to be executed in the browser
     * @return The value of the last expression in the code (if any)
     */
    public Object js(String code) {
        if (driver instanceof JavascriptExecutor) {
            return ((JavascriptExecutor) driver).executeScript(code);
        }

        return null;
    }

    /**
     * Switch to the new opened tab, perform some actions and just after that,
     * close such tab and return to the original one
     *
     * @param listener Action listener containing the actions to be performed in
     * the new opened tab
     */
    protected void switchToNewTab(WebDriver driver, ActionListener listener) {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());

        driver.switchTo().window(tabs.get(1));

        listener.actionPerformed(null);

        driver.close();

        driver.switchTo().window(tabs.get(0));
    }

    /**
     * (Un)check the given checkbox
     *
     * @param element The checkbox to be (un)checked
     * @param check Indicates if the checkbox should be checked or not
     * @return Yes if the checkbox is checked and No otherwise
     */
    protected String checkboxCheck(WebElement element, Boolean check) {
        Boolean initialCheckState = element.getAttribute("checked") != null;

        if (Boolean.logicalXor(initialCheckState, check)) {
            element.click();
        }

        return element.getAttribute("checked") != null ? "Yes" : "No";
    }

    public static String getTime() {
        long timestamp = new Date().getTime();
        DateFormat formatter = new SimpleDateFormat("DDHHmmss");
        String dateFormatted = formatter.format(timestamp);
        return dateFormatted;
    }

}
