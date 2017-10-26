package com.shiggins.constants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.testng.Assert;

public class PropertyManager {

    private static Properties props = null;

    private PropertyManager() {

    }

    public static String getProperty(String key) {
        if (props == null) {
            props = new Properties();
            try {

                FileInputStream fis = new FileInputStream("src/main/java/com/ascentis/constants/properties");

                props.load(fis);
            } catch (FileNotFoundException fne) {
                fne.printStackTrace();
                Assert.fail("Exception occured while reading properties file from ProperyManager.java file" + fne.getMessage());

            } catch (IOException ie) {
                ie.printStackTrace();
                Assert.fail("Exception occured while reading properties file from ProperyManager.java file" + ie.getMessage());
            }
        }

        return props.getProperty(key);

    }

}
