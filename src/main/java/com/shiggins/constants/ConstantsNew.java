package com.shiggins.constants;

import static com.shiggins.constants.WebDriverTest.prl;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;


public class ConstantsNew {

    private static final String PropertyFileLinux = "src/test/resources/Constants.propertiesLinux";
    private static final String PropertyFile = "src/test/resources/Constants.properties";
    private static final String PropertyFileIOS = "src/test/resources/Constants.propertiesMac";
    public static final String PropertyMap = "src/main/java/com/ascentis/constants/Constants.properties";
    public static final String ChromeDriverPath = GetPropertyValue("ChromeDriverPath");
    public static final String EnvironmentPath = GetPropertyValue("www.google.com");
    public static final String PhantomJsPath = GetPropertyValue("PhantomJSPath");
    public static final String IEDriverPath = GetPropertyValue("IEDriverPath");
    public static final String FirefoxDriverPath = GetPropertyValue("FirefoxPath");
    //Grid Configuration
    public static final String nodeURLXP = GetPropertyValue("nodeURLXP");
    public static final String nodeURLMAC = GetPropertyValue("nodeURLMAC");
    public static final String nodeURLW7 = GetPropertyValue("nodeURLW7");
    public static final String nodeURLW8 = GetPropertyValue("nodeURLW8");
    public static final String nodeURLVISTA = GetPropertyValue("nodeURLVISTA");
    public static final String nodeURLLINUX = GetPropertyValue("nodeURLLINUX");

    /*
     Reading a propertyfile and getting value of any property defined
     @Param PropertyName - Name of property for which you want to get value
     */
    public static Properties prop;
    static String PropertyValue;
    public static Properties prop2;
    static String PropertyValue2;

    public static String GetPropertyValue(String PropertyName) {
        prop = AccessPropertiesFile();

        try {
            PropertyValue = prop.getProperty(PropertyName);
        } catch (Exception e) {
            Assert.fail("Exception occured while reading properties file from GetPropertyValue method of constansNew.java file" + e.getMessage());
        }
        return PropertyValue;
    }

    public static Properties AccessPropertiesFile() {
        prop = new Properties();
        String IOS= System.getProperty("os.name");
        prl("operating system: "+IOS);
        // try retrieve data from proper Property File
        try {
            switch (IOS) {
                case "Linux":
                    prop.load(new FileInputStream(PropertyFileLinux));
                    break;
                case "Mac OS X":
                    prop.load(new FileInputStream(PropertyFileIOS));
                    break;
                default:
                    prop.load(new FileInputStream(PropertyFile));

            }} // catch exception in case properties file does not exist
        catch (IOException e) {
            Assert.fail("Exception occured while reading properties file from AccessPropertiesFile method of constansNew.java file" + e.getMessage());
        }
        return prop;
    }

}