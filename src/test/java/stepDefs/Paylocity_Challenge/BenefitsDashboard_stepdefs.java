package stepDefs.Paylocity_Challenge;

import com.shiggins.constants.WaitTool;
import com.shiggins.constants.WebDriverTest;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.*;
import stepDefs.BrowserSetUpAndTearDown;
import java.util.List;
import static com.shiggins.paylocity.abstracts.Login.loginUser;
import static com.shiggins.paylocity.abstracts.benefitsDashboard.addEmployeeButtonSubmit;
import static com.shiggins.paylocity.abstracts.benefitsDashboard.addEmployeeModal;
import static com.shiggins.paylocity.abstracts.benefitsDashboard.benefitCostList;
import static com.shiggins.paylocity.abstracts.benefitsDashboard.dependents;
import static com.shiggins.paylocity.abstracts.benefitsDashboard.dependentsList;
import static com.shiggins.paylocity.abstracts.benefitsDashboard.firstName;
import static com.shiggins.paylocity.abstracts.benefitsDashboard.firstNameList;
import static com.shiggins.paylocity.abstracts.benefitsDashboard.grossPayList;
import static com.shiggins.paylocity.abstracts.benefitsDashboard.lastName;
import static com.shiggins.paylocity.abstracts.benefitsDashboard.lastNameList;
import static com.shiggins.paylocity.abstracts.benefitsDashboard.netPayList;
import static com.shiggins.paylocity.abstracts.benefitsDashboard.salaryList;
import static com.shiggins.paylocity.abstracts.benefitsDashboard.submit;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by shiggins
 */
public class BenefitsDashboard_stepdefs extends WebDriverTest {

    private WebDriver driver;

    public BenefitsDashboard_stepdefs(BrowserSetUpAndTearDown bst) {
        this.driver = bst.driver;
    }

    @Given("^an Employer$")
    public void an_Employer() throws Throwable {
       
        prl("----------------------------------------------------------------------");
        prl(" BEGIN STEP --> the user Opens the application and Logs in with User Name and Password " + driver + "--" + driver.getTitle());
        com.shiggins.constants.WebDriverTest.reportTime();
        String HOSTURL = System.getProperty("host");
        driver.get(HOSTURL);

    }

    @Given("^I am on the Benefits Dashboard page$")
    public void i_am_on_the_Benefits_Dashboard_page() throws Throwable {

        loginUser(driver);

    }

    @When("^I select Add Employee$")
    public void i_select_Add_Employee() throws Throwable {
        

        addEmployeeButtonSubmit(driver);

    }

    @Then("^I should be able to enter employee details and save successfully:$")
    public void i_should_be_able_to_enter_employee_details_and_save_successfully(DataTable table1) throws Throwable {
      
        List<List<String>> data = table1.raw();

        String parentWindowHandler = driver.getWindowHandle();
        String subWindowHandler = null;

        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()) {
            subWindowHandler = iterator.next();
        }
        driver.switchTo().window(subWindowHandler);
        WaitTool.waitForElement(driver, addEmployeeModal, 60);

        WebDriverTest.verifyElementvisible(driver, addEmployeeModal);

        //First Name
        WebDriverTest.type(driver, firstName, (data.get(0).get(0)));

        // Laste Name
        WebDriverTest.type(driver, lastName, (data.get(0).get(1)));

        // Dependants
        WebDriverTest.type(driver, dependents, (data.get(0).get(2)));

        //Submit
        WebDriverTest.verifyAndClickElement(driver, submit);

        driver.switchTo().window(parentWindowHandler);
    }

    @Then("^I should see the employee in the table with correct cost benefit calculations:$")
    public void i_should_see_the_employee_in_the_table_with_correct_cost_benefit_calculations(DataTable table2) throws Throwable {
       

        List<List<String>> data = table2.raw();
       
       //Assert FName
       WebDriverTest.assertText(driver, firstNameList, (data.get(0).get(0)));
        //Assert LNanme
        WebDriverTest.assertText(driver, lastNameList, (data.get(0).get(1)));
        // Assert base salary
        WebDriverTest.assertText(driver, salaryList, (data.get(0).get(3)));
        // Dependents
        WebDriverTest.assertText(driver, dependentsList, (data.get(0).get(2)));
        //GrossPay
        WebDriverTest.assertText(driver, grossPayList, (data.get(0).get(4)));
        //Benefit Costs
        WebDriverTest.assertText(driver, benefitCostList, (data.get(0).get(5)));
        //NetPay
        WebDriverTest.assertText(driver, netPayList, (data.get(0).get(6)));
    }

}
