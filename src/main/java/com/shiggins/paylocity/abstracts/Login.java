package com.shiggins.paylocity.abstracts;

import static com.shiggins.constants.WebDriverTest.prl;
import java.util.concurrent.TimeUnit;

import com.shiggins.constants.WebDriverTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login extends com.shiggins.constants.WebDriverTest {

    public WebDriver driver = null;
    //public String CompUrl = "file:///C:/Users/shiggins.BSA/Desktop/ShanP/Paylocity/login.html";

    private static final By usernameInput = By.xpath("//*[@id='loginCard']//input[@name='form-username']");
    private static final By passwordInput = By.xpath("//*[@id='loginCard']//input[@name='form-password']");
    private static final By dashboard = By.xpath("//*[@id='header']/h1");
    private static final By loginButton = By.xpath("//*[@id='btnLogin']");
    private static final By dashHeader = By.xpath("//*[@id='header']/h1");
    
    public static void loginUser(WebDriver driver) {
        executeLogin(driver, "testUser", "Test1234");
    }

    public static void loginManager(WebDriver driver) {
        //manager Login
        executeLogin(driver, "test", "password");
    }

    private static void executeLogin(WebDriver driver, String username, String password) {
        //driver.get(url);
        String HOSTURL = System.getProperty("host");
        prl("HOSTURL= " + HOSTURL);
        driver.get(HOSTURL);
        //username
        WebDriverTest.type(driver, usernameInput, "testUser");

        // password
        WebDriverTest.type(driver, passwordInput, "Test1234");

        //Login
        WebDriverTest.verifyAndClickElement(driver, loginButton);

        WebDriverWait wait = new WebDriverWait(driver, WebDriverWait.DEFAULT_SLEEP_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(dashHeader));
        //assert Dashboard page
        WebDriverTest.assertText(driver, dashHeader, "Benefits Dashboard");
        
    }

}
