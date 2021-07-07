package Driver;

import java.io.File;

import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = {
		"src/test/resources/com/test/features" }, glue = "CoreImplementation", monochrome = true, plugin = {
				"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html" })

public class RunTest {

	public static void setup() {
		Reporter.loadXMLConfig(new File(System.getProperty("user.dir")+"src/test/resources/extent-config.xml"));
		Reporter.setSystemInfo("User Name", "Zain");
		Reporter.setSystemInfo("Application Name", "TG Assignment");
		Reporter.setSystemInfo("Operating System Type", System.getProperty("os.name").toString());
		Reporter.setTestRunnerOutput("Test Execution Cucumber Report");
	}
}