package stepDefs;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.Test;

import com.shiggins.constants.WebDriverTest;
import com.shiggins.constants.browser;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * This class contains @Before and @After cucumber annotated methods which will
 * be invoked before and after every scenario
 *
 * @author Shiggins
 *
 */
public class BrowserSetUpAndTearDown extends WebDriverTest {

    private Logger log = Logger.getLogger(BrowserSetUpAndTearDown.class);
    public WebDriver driver;
    browser b = new browser();

    @Before
    public void setUpBrowser(Scenario scenario) throws Exception {
        log.info("****************************************************************************************");

        log.info("$$$$$$$$$$$$$$$$$$$$$ Start of execution of scenario with name: " + scenario.getName() + "    $$$$$$$$$$$$$$$$$$$$$$$");
        prl("$$$$$$$$$$$$$$$$$$$$$ Start of execution of scenario with name: " + scenario.getName() + "    $$$$$$$$$$$$$$$$$$$$$$$");
        log.info("****************************************************************************************");
        String sName = System.getProperty("BrowserName");
        System.out.println("browser name BrowserSetUpAndTearDown: " + sName);
        this.driver = b.setUp(System.getProperty("BrowserName"), System.getProperty("BrowserVersion"), System.getProperty("Environment"), System.getProperty("OSPlatform"));
    }

    @After
    public void tearDown(Scenario scenario) throws Exception {

        log.info("****************************************************************************************");

        log.info("$$$$$$$$$$$$$$$$$$$$$ Completion of execution of scenario with name: " + scenario.getName() + "    $$$$$$$$$$$$$$$$$$$$$$$");
        prl("$$$$$$$$$$$$$$$$$$$$$ Completion of execution of scenario with name: " + scenario.getName() + "    $$$$$$$$$$$$$$$$$$$$$$$");
        log.info("****************************************************************************************");
        System.out.println("driver value:" + driver);

        if (scenario.isFailed() && (!System.getProperty("BrowserName").equalsIgnoreCase("HtmlUnitDriver"))) {

            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }
        }

        b.tearDown();
    }

}
