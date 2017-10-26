package com.shiggins.constants;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.shiggins.constants.PropertyManager.*;
import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.IMethodInstance;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class WebDriverSelectors {
	
	

	
	
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
			Assert.fail("Exception occured while finding element with locator:"+by+" , Exception message:"+ex.getMessage());
		}
		return null;
	}

	/**
	 * Method to select a drop down box
	 * 
	 * @param driver
	 * @param by
	 * @param value
	 */

	public static void selectDropDown(WebDriver driver, By by, String value) {

		try {
			WebElement elmement = driver.findElement(by);
			assertNotNull(elmement);
			Select selctBox = new Select(elmement);
			selctBox.selectByVisibleText(value);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail("Exception occured while selected text:"+value+" from dropdown with locator:"+by+" , Exception message:"+ex.getMessage());
		}

	}

	/**
	 * Method to verify the text of an element
	 * 
	 * @param driver
	 * @param by
	 * @param value
	 * @return
	 */
	public static boolean verifyText(WebDriver driver, By by, String value) {
		WebElement element = getElement(driver, by);
		assertNotNull(element);
		String text = element.getText();
		if (value.equals(text)) {
			return true;
		} else {
			return false;
		}

	}
	
	/**
	 * To verify if element is present or not.
	 * 
	 * @param driver
	 * @param by
	 * @return
	 */
	public static void verifyElementPresent(WebDriver driver, By by) {

		assertNotNull(getElement(driver, by));

	}

	/**
	 * To verify if element is present or not.
	 * 
	 * @param driver
	 * @param by
	 * @return
	 */
	public static boolean isElementPresent(WebDriver driver, By by) {

		if (getElement(driver, by) == null) {
			return true;
		}
		return false;
	}

	/**
	 * Verify the element and click on an element
	 * 
	 * @param driver
	 * @param by
	 */
	public static void verifyAndClickElement(WebDriver driver, By by) {
		try{
			WebElement elm = getElement(driver, by);
			assertNotNull(elm);
			elm.click();
		}
		catch(Exception e){
			Assert.fail("Exception occurred while clicking on element with locator:"+by+" , Exception message:"+e.getMessage());
		}

	}

	/**
	 * Type on the element
	 * 
	 * @param driver
	 * @param by
	 * @param value
	 */
	public static void type(WebDriver driver, By by, String value) {

		try {
			WebElement elm = getElement(driver, by);
			assertNotNull(elm);
			elm.sendKeys(value);

		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail("Exception occured while entering text:"+value+" in web element with locator:"+by+" ,  Exception message:"+ex.getMessage());
		}

	}

	/**
	 * Wait for element present
	 * 
	 * @param driver
	 * @param by
	 * @param timeInSecs
	 */
	public static void waitForElementPresent(WebDriver driver, By by,
			int timeInSecs) {

		try {
			new WebDriverWait(driver, timeInSecs).until(ExpectedConditions
					.presenceOfElementLocated(by));

		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail("Web Element with locator: "+by+" is not displayed. Exception message: "+ex.getMessage());
		}

	}
	/**
	 * This method is used to execute a script.
	 * @param script
	 */
	public void excuteJavaScript(WebDriver driver,String script){
		try{
			((JavascriptExecutor )driver).executeScript(script);
		}
		catch(Exception e){
			Assert.fail("Exception occured while executing java script ");
		}
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
	
	
	
	
	

}
