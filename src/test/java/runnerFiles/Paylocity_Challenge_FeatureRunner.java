package runnerFiles;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * This cucumber runner class is used to run ALL "Paylocity_Challenge" features
 * @author Shiggins 
 *
 */

@CucumberOptions(features = "src/test/java/features/Paylocity/Paylocity_Challenge/",glue = "stepDefs",tags = {"~@ignore"},
plugin = {"pretty","json:Automation Reports/Latest Results/Json Reports/Paylocity_Challenge.json","html:Automation Reports/Latest Results/Html Reports/Paylocity_Challenge"})
public class Paylocity_Challenge_FeatureRunner extends AbstractTestNGCucumberTests {
	
}
