package CoreImplementation;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefs {

	UIAutomation uIAutomations = UIAutomation.getInstance();
	APIAutomation apiAutomation = APIAutomation.getInstance();
	private String chromDriverPath = "/src/test/resources/com/test/driver/chromedriver";
	private String currentDirectory = System.getProperty("user.dir");

	public static WebDriver driver;

	public StepDefs() {
		System.setProperty("webdriver.chrome.driver", currentDirectory + chromDriverPath);
		System.out.println("Chrome Driver Location:- " + currentDirectory + chromDriverPath);

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Given("^I open the page (.+)$")
	public void openPage(String url) {
		uIAutomations.openURL(url);
	}

	@Then("^I validate the count of rows for table (.+) and (.+)$")
	public void validateRowCount(String tBody, String rowSerial) throws InterruptedException {
		uIAutomations.getRowCount(tBody, rowSerial);
	}

	@Then("^I validate the (.+) by using (.+) in between (.+) (.+)$")
	public void validateMarketCap(String table, String marketValueCap, String minRange, String maxRange)
			throws IOException, InterruptedException {
		uIAutomations.validateFilters(table, marketValueCap, minRange, maxRange);
	}

	@When("^I click on the link (.+)$")
	public void ClickOnLinkByText(String xpath) {
		uIAutomations.clickByXpath(xpath);
	}

	@Given("^User converts the price using below data (.+) (.+) (.+) (.+) (.+) (.+) (.+)$")
	public void convertPrice(String getIdKey, String currency, String jsonId, String jsonAmountKey,
			String jsonAmountValue, String jsonConvertId, String BolivanoId) throws IOException {
		apiAutomation.convertPrice(getIdKey, currency, jsonId, jsonAmountKey, jsonAmountValue, jsonConvertId,
				BolivanoId);
	}
	
	@Then("^AssertEquals (.+) with expected (.+)$")
	public void AssertEqulsAttributes(String jsonPath, String expectedVal) throws IOException {
		apiAutomation.assertEqualsJsonAttributes(jsonPath, expectedVal);
	}
	
	@Then("^AssertTrue (.+) with expected (.+)$")
	public void AssertTrueAttributes(String jsonPath, String expectedVal) throws IOException {
		apiAutomation.assertTrueJsonAttributes(jsonPath, expectedVal);
	}

	@Given("^Scenario (.+)$")
	public void printScenarioName(String scenarioName) {
		System.out.println("######################## SCENARIO ########################");
		System.out.println("######################## " + scenarioName + " ########################");
	}

	@After
	public void quitBrowser() {
		driver.quit();
		System.out.println("Quiting Browser");
	}
}
