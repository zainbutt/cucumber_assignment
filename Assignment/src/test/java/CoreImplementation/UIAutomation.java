package CoreImplementation;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ReadingConfig.TestDataReadConfig;
import ReadingConfig.WebElementsReadConfig;

public class UIAutomation{
/**
 * static Singleton instance.
 */
private static volatile UIAutomation instance;
private TestDataReadConfig readTestData = TestDataReadConfig.getInstance();
private WebElementsReadConfig readWebElementConfig = WebElementsReadConfig.getInstance();

/**
 * Private constructor for singleton.
 */
	private UIAutomation() {
	}

/**
 * Return a singleton instance of UIAutomation.
 */
public static UIAutomation getInstance() {
	// Double lock for thread safety.
	if (instance == null) {
		synchronized (UIAutomation.class) {
			if (instance == null) {
				instance = new UIAutomation();
			}
		}
	}
	return instance;
}	
	
	public void openURL(String url) {

		try {
			String pageUrl = readTestData.readDataConfig(url);
			StepDefs.driver.get(pageUrl);
			System.out.println("URL: " + pageUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void clickByXpath(String xpath) {
		try {
			String getXpathFromConfig = readWebElementConfig.readWebElementConfig(xpath);
			StepDefs.driver.findElement(By.xpath(getXpathFromConfig)).click();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getRowCount(String element, String rowSerialXPath) throws InterruptedException {
		try {
			String getSerialNumber = null;
			String updatedXpath = null;
			WebDriverWait wait = new WebDriverWait(StepDefs.driver, 20);
			String getTableXpathFromConfig = readWebElementConfig.readWebElementConfig(element);
			String getRowSerialFromConfig = readWebElementConfig.readWebElementConfig(rowSerialXPath);
			List<WebElement> rows = StepDefs.driver.findElements(By.xpath(getTableXpathFromConfig));
			System.out.println("Size: " + rows.size());

			for (int count = 1; count <= rows.size(); count++) {
				boolean isRowDisplayed = StepDefs.driver
						.findElement(By.xpath(getTableXpathFromConfig + "['" + count + "']")).isDisplayed();
				updatedXpath = getRowSerialFromConfig.replaceAll("DYNAMIC_VALUE", String.valueOf(count));
				StepDefs.driver.findElement(By.tagName("body")).sendKeys(Keys.SPACE);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(updatedXpath)));
				getSerialNumber = StepDefs.driver.findElement(By.xpath(updatedXpath)).getText();

				System.out.println("Row Count --> " + count);
				System.out.println("Serial Number --> " + getSerialNumber);

				Assert.assertEquals(getSerialNumber, String.valueOf(count));
				Assert.assertTrue(isRowDisplayed);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void validateFilters(String table, String maxCap, String minRange, String maxRange)
			throws IOException, InterruptedException {
		String updatedXpathForMaxCapValues = null;
		String maxCapValue = null;

		WebDriverWait wait = new WebDriverWait(StepDefs.driver, 20);
		String getTableXpathFromConfig = readWebElementConfig.readWebElementConfig(table);
		String getMaxCapFromConfig = readWebElementConfig.readWebElementConfig(maxCap);
		String getMinRangeFromConfig = readTestData.readDataConfig(minRange);
		String getMaxRangeFromConfig = readTestData.readDataConfig(maxRange);

		Thread.sleep(3000);
		List<WebElement> rows = StepDefs.driver.findElements(By.xpath(getTableXpathFromConfig));
		System.out.println("Size: " + rows.size());

		for (int count = 1; count <= rows.size(); count++) {
			boolean isRowDisplayed = StepDefs.driver
					.findElement(By.xpath(getTableXpathFromConfig + "['" + count + "']")).isDisplayed();

			updatedXpathForMaxCapValues = getMaxCapFromConfig.replaceAll("DYNAMIC_VALUE", String.valueOf(count));

			StepDefs.driver.findElement(By.tagName("body")).sendKeys(Keys.SPACE);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(updatedXpathForMaxCapValues)));
			maxCapValue = StepDefs.driver.findElement(By.xpath(updatedXpathForMaxCapValues)).getText();

			String removingCommaFromMaxCap = maxCapValue.replaceAll(",", "");
			String maxCap1 = removingCommaFromMaxCap.replace("$", "");

			System.out.println("Max Cap value:- " + maxCap1);
			System.out.println("Row Count --> " + count);

			Long getMaxCap = Long.valueOf(maxCap1);

			Assert.assertTrue(isRowDisplayed);
			if (Long.valueOf(getMinRangeFromConfig) < getMaxCap && Long.valueOf(getMaxRangeFromConfig) > getMaxCap) {
				System.out.println("Condition Matched:- " + Long.valueOf(getMinRangeFromConfig) + " < " + getMaxCap
						+ " && " + Long.valueOf(getMaxRangeFromConfig) + " > " + getMaxCap);
				Assert.assertTrue(Long.valueOf(getMinRangeFromConfig) < getMaxCap
						&& Long.valueOf(getMaxRangeFromConfig) > getMaxCap);
			} else {
				System.out.println("Condition NOT Matched:- " + Long.valueOf(getMinRangeFromConfig) + " < " + getMaxCap
						+ " && " + Long.valueOf(getMaxRangeFromConfig) + " > " + getMaxCap);
				Assert.assertTrue(Long.valueOf(getMinRangeFromConfig) < getMaxCap
						&& Long.valueOf(getMaxRangeFromConfig) > getMaxCap);
			}

		}

	}

}