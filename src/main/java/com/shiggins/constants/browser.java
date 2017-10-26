package com.shiggins.constants;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
//import org.seleniumhq.selenium;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.Reporter;

public class browser {

    public WebDriver driver;

    /*
		 * WebDriver Setup method which helps to launch respective browser for test per given parameters
		 * @param BrowserName - Browser name
		 * @param BrowserVersion - Browser version
		 * @param OSPlatform - OS platform
		 * @param Environment - Execution environment i.e. local or grid
		 * author Shiggins
     */
    public WebDriver setUp(String BrowserName, String BrowserVersion, String Environment, String OSPlatform) {
        try {
            if (Environment.equalsIgnoreCase("Local")) {
                //For launching Firefox browser
                if (BrowserName.equalsIgnoreCase("Firefox")) {

//                                                                                                                ProfilesIni profile = new ProfilesIni();
//                                                                                                                  //on win 7 look in C:\Users\####.BSA\AppData\Roaming\Mozilla\Firefox\Profiles
//                                                                                                                FirefoxProfile myprofile = profile.getProfile("default");
//                                                                                                                 driver = new FirefoxDriver(myprofile);
                    //  System.setProperty("webdriver.firefox.marionette","G:\\Selenium\\Firefox driver\\geckodriver.exe");
                    System.setProperty("webdriver.gecko.driver", ConstantsNew.FirefoxDriverPath);
                    DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                    capabilities.setCapability("marionette", true);
                    //WebDriver driver = new FirefoxDriver(capabilities);
                    driver = new FirefoxDriver();
                    // driver = new FirefoxDriver();
                    Reporter.log("--------------------------- Launching Firefox Browser on Local machine --------------------------- ");
                    System.out.println("---------------------------  Launching Firefox Browser on Local machine --------------------------- ");
                } //for launching Google Chrome browser
                else if (BrowserName.equalsIgnoreCase("chrome")) {
                    System.setProperty("webdriver.chrome.driver", ConstantsNew.ChromeDriverPath);
                    ChromeOptions options = new ChromeOptions();

                    //options.setBinary("C:\\Desktop\\DART\\dart\\chromium\\chrome.exe");
                    DesiredCapabilities capabilities = DesiredCapabilities.chrome();

//                                          https://sites.google.com/a/chromium.org/chromedriver/logging/performance-log
//                                           ChromeDriver supports performance logging, from which you can get events of domains "Timeline", "Network", and "Page", as well as trace data for specified trace categories.
//                                            LoggingPreferences loggingprefs = new LoggingPreferences();
//                                            loggingprefs.enable(LogType.BROWSER, Level.ALL);
//                                          capabilities.setCapability(CapabilityType.LOGGING_PREFS, loggingprefs);
                    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                    options.addArguments("--disable-extensions");
                    options.addArguments("--start-maximized");
                    options.addArguments("--test-type");
                    options.addArguments("disable-infobars");

//						driver = new ChromeDriver(options);
                    driver = new ChromeDriver(capabilities);

                    Reporter.log("--------------------------- Launching Chrome Browser on Local machine -----------------------");
                    System.out.println("--------------------------- Launching Chrome Browser on Local machine --------------------------- ");
                } else if (BrowserName.equalsIgnoreCase("InternetExplorer")) {
                    System.setProperty("webdriver.ie.driver", ConstantsNew.IEDriverPath);
                    driver = new InternetExplorerDriver();
                    Reporter.log("--------------------------- Launching Internet Explorer on Local machine -----------------------");
                    System.out.println("--------------------------- Launching Internet Explorer on Local machine --------------------------- ");
                } else if (BrowserName.equalsIgnoreCase("HtmlUnitDriver")) {
                    driver = new HtmlUnitDriver();
                    Reporter.log("--------------------------- Launching Html Unit Driver on Local machine -----------------------");
                    System.out.println("--------------------------- Launching Html Unit Driver on Local machine --------------------------- ");
                } else if (BrowserName.equalsIgnoreCase("phantomJS")) {
                    //System.setProperty("phantomjs.binary.path",ConstantsNew.PhantomJsPath);
                    Capabilities caps = new DesiredCapabilities();
                    ((DesiredCapabilities) caps).setJavascriptEnabled(true);
                    ((DesiredCapabilities) caps).setCapability("takesScreenshot", true);
                    ((DesiredCapabilities) caps).setCapability(
                            PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                            ConstantsNew.PhantomJsPath
                    );
                    driver = new PhantomJSDriver(caps);
                    Reporter.log("--------------------------- Launching phantom js Driver on Local machine -----------------------");
                    System.out.println("--------------------------- Launching phantom js Driver on Local machine --------------------------- ");
                }

            } else if (Environment.equalsIgnoreCase("GRID")) {
                //For Grid Configuration
                //GRID XP 
                if (OSPlatform.equalsIgnoreCase("XP")) {
                    //For launching Firefox on GRID XP machine
                    if (BrowserName.equalsIgnoreCase("Firefox")) {
                        DesiredCapabilities capability = DesiredCapabilities.firefox();
                        capability.setBrowserName("firefox");
                        capability.setVersion(BrowserVersion);
                        capability.setPlatform(Platform.XP);
                        driver = new RemoteWebDriver(new URL(ConstantsNew.nodeURLXP), capability);
                        System.out.println("--------------------------- launching Firefox Browser on GRID XP machine at " + ConstantsNew.nodeURLXP + "--------------------------- ");
                    } //For launching Google Chrome on GRID XP machine
                    else if (BrowserName.equalsIgnoreCase("Chrome")) {
                        DesiredCapabilities capability = DesiredCapabilities.chrome();
                        capability.setBrowserName("chrome");
                        capability.setVersion(BrowserVersion);
                        capability.setPlatform(Platform.XP);
                        driver = new RemoteWebDriver(new URL(ConstantsNew.nodeURLXP), capability);

                        System.out.println("--------------------------- launching Google Chrome Browser on GRID XP machine at " + ConstantsNew.nodeURLXP + "--------------------------- ");

                    } //For launching Google Chrome on GRID XP machine
                    else if (BrowserName.equalsIgnoreCase("InternetExplorer")) {
                        DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
                        capability.setBrowserName("internetexplorer");
                        capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                        capability.setVersion(BrowserVersion);
                        capability.setPlatform(Platform.XP);

                        driver = new RemoteWebDriver(new URL(ConstantsNew.nodeURLXP), capability);
                        System.out.println("--------------------------- launching Internet Explorer on GRID XP machine at " + ConstantsNew.nodeURLXP + "--------------------------- ");

                    } //GRID Windows LINUX
                    else if (OSPlatform.equalsIgnoreCase("LINUX")) {
                        //For lunching Firefox on GRID Windows VISTA machine
                        if (BrowserName.equalsIgnoreCase("Firefox")) {
                            DesiredCapabilities capability = DesiredCapabilities.firefox();
                            capability.setBrowserName("firefox");
                            //				capability.setCapability("binary", "/ms/dist/fsf/PROJ/firefox/16.0.0/bin/firefox")
                            capability.setVersion(BrowserVersion);
                            capability.setPlatform(Platform.LINUX);

                            driver = new RemoteWebDriver(new URL(ConstantsNew.nodeURLLINUX), capability);
                            System.out.println("--------------------------- Launching Firefox Browser on GRID Windows LINUX machine at " + ConstantsNew.nodeURLLINUX + "--------------------------- ");
                        } //For lunching Google Chrome on GRID Windows VISTA machine
                        else if (BrowserName.equalsIgnoreCase("GoogleChrome")) {
                            DesiredCapabilities capability = DesiredCapabilities.chrome();
                            capability.setBrowserName("chrome");
                            capability.setVersion(BrowserVersion);
                            capability.setPlatform(Platform.LINUX);

                            driver = new RemoteWebDriver(new URL(ConstantsNew.nodeURLLINUX), capability);
                            System.out.println("--------------------------- Launching Google Chrome Browser on GRID Windows LINUX machine at " + ConstantsNew.nodeURLLINUX + "--------------------------- ");

                        }

                    }

                } //GRID MAC
                else if (OSPlatform.equalsIgnoreCase("MAC")) {
                    //For launching Firefox on GRID MAC machine
                    if (BrowserName.equalsIgnoreCase("Firefox")) {
                        DesiredCapabilities capability = DesiredCapabilities.firefox();
                        capability.setBrowserName("firefox");
                        capability.setVersion(BrowserVersion);
                        capability.setPlatform(Platform.MAC);

                        driver = new RemoteWebDriver(new URL(ConstantsNew.nodeURLMAC), capability);
                        System.out.println("--------------------------- launching Firefox Browser on GRID MAC machine at " + ConstantsNew.nodeURLMAC + "--------------------------- ");
                    } //For launching Google Chrome on GRID MAC machine
                    else if (BrowserName.equalsIgnoreCase("GoogleChrome")) {
                        DesiredCapabilities capability = DesiredCapabilities.chrome();
                        capability.setBrowserName("chrome");
                        capability.setVersion(BrowserVersion);
                        capability.setPlatform(Platform.MAC);

                        driver = new RemoteWebDriver(new URL(ConstantsNew.nodeURLMAC), capability);
                        System.out.println("--------------------------- launching Google Chrome Browser on GRID MAC machine at " + ConstantsNew.nodeURLMAC + "--------------------------- ");

                    }

                } //GRID Windows 7
                else if (OSPlatform.equalsIgnoreCase("W7")) {

                    //For launching Firefox on GRID Windows 7 machine
                    if (BrowserName.equalsIgnoreCase("Firefox")) {
                        DesiredCapabilities capability = DesiredCapabilities.firefox();
                        capability.setBrowserName("firefox");
                        capability.setVersion(BrowserVersion);
                        capability.setPlatform(Platform.WINDOWS);

                        driver = new RemoteWebDriver(new URL(ConstantsNew.nodeURLW7), capability);
                        System.out.println("--------------------------- launching Firefox Browser on GRID Windows 7 machine at " + ConstantsNew.nodeURLW7 + "--------------------------- ");
                    } //For launching Google Chrome on GRID Windows 7 machine
                    else if (BrowserName.equalsIgnoreCase("Chrome")) {
                        DesiredCapabilities capability = DesiredCapabilities.chrome();
                        capability.setBrowserName("chrome");
                        capability.setVersion(BrowserVersion);
                        capability.setPlatform(Platform.WINDOWS);

                        driver = new RemoteWebDriver(new URL(ConstantsNew.nodeURLW7), capability);
                        System.out.println("--------------------------- launching Google Chrome Browser on GRID Windows 7 machine at " + ConstantsNew.nodeURLW7 + "--------------------------- ");

                    } //For launching Google Chrome on GRID Windows 7 machine
                    else if (BrowserName.equalsIgnoreCase("InternetExplorer")) {
                        DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
                        capability.setBrowserName("internetexplorer");
                        capability.setVersion(BrowserVersion);
                        capability.setPlatform(Platform.WINDOWS);

                        driver = new RemoteWebDriver(new URL(ConstantsNew.nodeURLW7), capability);
                        System.out.println("--------------------------- launching Internet Explorer on GRID Windows 7 machine at " + ConstantsNew.nodeURLW7 + "--------------------------- ");

                    }

                } //GRID Windows 8
                else if (OSPlatform.equalsIgnoreCase("W8")) {
                    //For launching Firefox on GRID Windows 8 machine
                    if (BrowserName.equalsIgnoreCase("Firefox")) {
                        DesiredCapabilities capability = DesiredCapabilities.firefox();
                        capability.setBrowserName("firefox");
                        capability.setVersion(BrowserVersion);
                        capability.setPlatform(Platform.WIN8);

                        driver = new RemoteWebDriver(new URL(ConstantsNew.nodeURLW8), capability);
                        System.out.println("--------------------------- launching Firefox Browser on GRID Windows 8 machine at " + ConstantsNew.nodeURLW8 + "--------------------------- ");
                    } //For launching Google Chrome on GRID Windows 8 machine
                    else if (BrowserName.equalsIgnoreCase("Chrome")) {
                        DesiredCapabilities capability = DesiredCapabilities.chrome();
                        capability.setBrowserName("chrome");
                        capability.setVersion(BrowserVersion);
                        capability.setPlatform(Platform.WIN8);

                        driver = new RemoteWebDriver(new URL(ConstantsNew.nodeURLW8), capability);
                        System.out.println("--------------------------- launching Google Chrome Browser on GRID Windows 8 machine at " + ConstantsNew.nodeURLW8 + "--------------------------- ");

                    } //For launching Google Chrome on GRID Windows 8 machine
                    else if (BrowserName.equalsIgnoreCase("InternetExplorer")) {
                        DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
                        capability.setBrowserName("internetexplorer");
                        capability.setVersion(BrowserVersion);
                        capability.setPlatform(Platform.WIN8);

                        driver = new RemoteWebDriver(new URL(ConstantsNew.nodeURLW8), capability);
                        System.out.println("--------------------------- launching Internet Explorer on GRID Windows 8 machine at " + ConstantsNew.nodeURLW8 + "--------------------------- ");

                    }

                } //GRID Windows VISTA
                else if (OSPlatform.equalsIgnoreCase("VISTA")) {
                    //For launching Firefox on GRID Windows VISTA machine
                    if (BrowserName.equalsIgnoreCase("Firefox")) {
                        DesiredCapabilities capability = DesiredCapabilities.firefox();
                        capability.setBrowserName("firefox");
                        capability.setVersion(BrowserVersion);
                        capability.setPlatform(Platform.VISTA);

                        driver = new RemoteWebDriver(new URL(ConstantsNew.nodeURLVISTA), capability);
                        System.out.println("--------------------------- launching Firefox Browser on GRID Windows VISTA machine at " + ConstantsNew.nodeURLVISTA + "--------------------------- ");
                    } //For launching Google Chrome on GRID Windows VISTA machine
                    else if (BrowserName.equalsIgnoreCase("Chrome")) {
                        DesiredCapabilities capability = DesiredCapabilities.chrome();
                        capability.setBrowserName("chrome");
                        capability.setVersion(BrowserVersion);
                        capability.setPlatform(Platform.VISTA);

                        driver = new RemoteWebDriver(new URL(ConstantsNew.nodeURLVISTA), capability);
                        System.out.println("--------------------------- launching Google Chrome Browser on GRID Windows VISTA machine at " + ConstantsNew.nodeURLVISTA + "--------------------------- ");

                    } //For launching Google Chrome on GRID Windows VISTA machine
                    else if (BrowserName.equalsIgnoreCase("InternetExplorer")) {
                        DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
                        capability.setBrowserName("internetexplorer");
                        capability.setVersion(BrowserVersion);
                        capability.setPlatform(Platform.VISTA);

                        driver = new RemoteWebDriver(new URL(ConstantsNew.nodeURLVISTA), capability);
                        System.out.println("--------------------------- launching Internet Explorer on GRID Windows VISTA machine at " + ConstantsNew.nodeURLVISTA + "--------------------------- ");

                    }
                }

            }

            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            System.out.println("from browser class:" + driver);
        } catch (Exception e) {
            Assert.fail("Exception occured while invoking browser", e);
        }
        return driver;
    }

    /*
		WebDriver tear down method which closes browser after each test
     */
    public void tearDown() {
        try {
            driver.close();
            driver.quit();
        } catch (Exception e) {
            Assert.fail("Exception occured while closing browser instance, exception message:" + e.getMessage());
        }
    }

}

