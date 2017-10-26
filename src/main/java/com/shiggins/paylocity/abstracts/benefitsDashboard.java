package com.shiggins.paylocity.abstracts;

import com.shiggins.constants.WaitTool;
import static com.shiggins.constants.WebDriverTest.prl;
import java.util.concurrent.TimeUnit;

import com.shiggins.constants.WebDriverTest;
import cucumber.api.DataTable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class benefitsDashboard extends com.shiggins.constants.WebDriverTest {

    public WebDriver driver = null;
    
    public static By addEmployeeModal = By.xpath("//*[@id='addEmployeeModal']");
    public static By firstName = By.xpath("(//*[@id='employees-form']//input)[1]");
    public static By lastName = By.xpath("(//*[@id='employees-form']//input)[2]");
    public static By submit = By.xpath("//*[@id='employees-form']/div[4]/div/button[1]");
    public static By dependents = By.xpath("(//*[@id='employees-form']//input)[3]");  
    public static By lastNameList = By.xpath("//*[@id='employee-table']//tr[2]/td[2]");
    public static By firstNameList = By.xpath("//*[@id='employee-table']//tr[2]/td[3]");
    public static By salaryList = By.xpath("//*[@id='employee-table']//tr[2]/td[4]");
    public static By dependentsList = By.xpath("//*[@id='employee-table']//tr[2]/td[5]");
    public static By grossPayList = By.xpath("//*[@id='employee-table']//tr[2]/td[6]");
    public static By benefitCostList = By.xpath("//*[@id='employee-table']//tr[2]/td[7]");
    public static By netPayList = By.xpath("//*[@id='employee-table']//tr[2]/td[8]");
    

    private static final By btnAddEmployee = By.xpath("//*[@id='btnAddEmployee']");


    public static void addEmployeeButtonSubmit(WebDriver driver) {
       
      // click Add employee
     WebDriverTest.verifyAndClickElement(driver,btnAddEmployee );
    }
    
    
   private static void addEmployeeInfo(WebDriver driver, DataTable table1) {

       
       List<List<String>> data = table1.raw();
       
       String parentWindowHandler = driver.getWindowHandle();
            String subWindowHandler = null;

            Set<String> handles = driver.getWindowHandles();
            Iterator<String> iterator = handles.iterator();
            while (iterator.hasNext()){
              subWindowHandler = iterator.next();
            }
            driver.switchTo().window(subWindowHandler);
              WaitTool.waitForElement(driver, By.xpath("//*[@id='addEmployeeModal']"), 60);

          
           WebDriverTest.verifyElementvisible(driver,By.xpath("//*[@id='addEmployeeModal']"));
         

//First Namre
       WebDriverTest.type(driver, By.xpath("(//*[@id='employees-form']//input)[1]"), (data.get(0).get(0)));

// Laste Name
       WebDriverTest.type(driver, By.xpath("(//*[@id='employees-form']//input)[2]"), (data.get(0).get(1)));

// Dependants
       WebDriverTest.type(driver, By.xpath("(//*[@id='employees-form']//input)[3]"), (data.get(0).get(2)));

//Submit
       WebDriverTest.verifyAndClickElement(driver, By.xpath("//*[@id='employees-form']/div[4]/div/button[1]"));

       driver.switchTo().window(parentWindowHandler);

    }

}
