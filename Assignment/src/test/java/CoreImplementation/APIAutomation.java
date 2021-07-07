package CoreImplementation;

import java.io.IOException;

import org.junit.Assert;

import ReadingConfig.TestDataReadConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APIAutomation {

	TestDataReadConfig readTestData = TestDataReadConfig.getInstance();
	/**
	 * static Singleton instance.
	 */
	private static volatile APIAutomation instance;

	/**
	 * Private constructor for singleton.
	 */
	private APIAutomation() {
	}

	/**
	 * Return a singleton instance of APIAutomation.
	 */
	public static APIAutomation getInstance() {
		// Double lock for thread safety.
		if (instance == null) {
			synchronized (APIAutomation.class) {
				if (instance == null) {
					instance = new APIAutomation();
				}
			}
		}
		return instance;
	}

	public String getIds(String key, String currency, String IdKey) throws IOException {
		io.restassured.RestAssured.baseURI = readTestData.readDataConfig("BASE.URL");
		String apiKeyName = readTestData.readDataConfig("API.KEY.NAME");
		String apiKeyValue = readTestData.readDataConfig("API.KEY.VALUE");
		
		Response response = io.restassured.RestAssured.given()
				.header(apiKeyName, apiKeyValue).contentType(ContentType.JSON)
				.param(key, currency).when().get("/cryptocurrency/map").then().extract().response();

		System.out.println(response.asPrettyString());
		System.out.println(response.jsonPath().getString(IdKey).replace("[", "").replace("]", ""));

		return response.jsonPath().getString(IdKey).replace("[", "").replace("]", "");
	}

	public void convertPrice(String getIdKey, String currency, String jsonId, String jsonAmountKey, String jsonAmountValue, String jsonConvertId, String jsonConvertValue) throws IOException {
		io.restassured.RestAssured.baseURI = readTestData.readDataConfig("BASE.URL");
		String apiKeyName = readTestData.readDataConfig("API.KEY.NAME");
		String apiKeyValue = readTestData.readDataConfig("API.KEY.VALUE");
		String getIdKeyFromConfig = readTestData.readDataConfig(getIdKey);
		String getIdCurrencyFromConfig = readTestData.readDataConfig(currency);
		String getJsonIdFromConfig = readTestData.readDataConfig(jsonId);
		String getJsonAmountKeyFromConfig = readTestData.readDataConfig(jsonAmountKey);
		String getJsonAmountValueFromConfig = readTestData.readDataConfig(jsonAmountValue);
		String getJsonConvertIdFromConfig = readTestData.readDataConfig(jsonConvertId);
		String getJsonConvertValueFromConfig = readTestData.readDataConfig(jsonConvertValue);
		
		Response response = io.restassured.RestAssured.given()
				.header(apiKeyName, apiKeyValue).contentType(ContentType.JSON)
				.param("id", getIds(getIdKeyFromConfig, getIdCurrencyFromConfig, getJsonIdFromConfig).toString()).param(getJsonAmountKeyFromConfig, getJsonAmountValueFromConfig).param(getJsonConvertIdFromConfig, getJsonConvertValueFromConfig).when()
				.get("/tools/price-conversion").then().extract().response();

		System.out.println(response.asPrettyString());
	}

	public void assertEqualsJsonAttributes(String jsonPath, String expectedValue) throws IOException {
		io.restassured.RestAssured.baseURI = readTestData.readDataConfig("BASE.URL");
		
		String apiKeyName = readTestData.readDataConfig("API.KEY.NAME");
		String apiKeyValue = readTestData.readDataConfig("API.KEY.VALUE");
		
		String getJsonPath = readTestData.readDataConfig(jsonPath);
		String getExpectedValue = readTestData.readDataConfig(expectedValue);

		Response response = io.restassured.RestAssured.given()
				.header(apiKeyName, apiKeyValue).contentType(ContentType.JSON)
				.param("id", "1027").when().get("/cryptocurrency/info").then().extract().response();

		System.out.println(response.asPrettyString());
		System.out.println("====================================================================================");
		System.out.println("Actual Result:- "+ response.jsonPath().getString(getJsonPath));
		System.out.println("Expected Result:- "+ getExpectedValue);
		System.out.println("====================================================================================");

		Assert.assertEquals(response.jsonPath().getString(getJsonPath), getExpectedValue);
			
	}
	
	public void assertTrueJsonAttributes(String jsonPath, String expectedValue) throws IOException {
		io.restassured.RestAssured.baseURI = readTestData.readDataConfig("BASE.URL");
		
		String apiKeyName = readTestData.readDataConfig("API.KEY.NAME");
		String apiKeyValue = readTestData.readDataConfig("API.KEY.VALUE");
		
		String getJsonPath = readTestData.readDataConfig(jsonPath);
		String getExpectedValue = readTestData.readDataConfig(expectedValue);

		Response response = io.restassured.RestAssured.given()
				.header(apiKeyName, apiKeyValue).contentType(ContentType.JSON)
				.param("id", "1027").when().get("/cryptocurrency/info").then().extract().response();

		System.out.println(response.asPrettyString());

		boolean state = response.jsonPath().getString(getJsonPath).contains(getExpectedValue);

		if(state) {
			System.out.println("====================================================================================");
			System.out.println("Actual Result:- "+ response.jsonPath().getString(getJsonPath).replace("[", "").replace("]", ""));
			System.out.println("Expected Result:- "+ getExpectedValue);
			System.out.println("====================================================================================");
			
			Assert.assertTrue(state);	
		}else {
			System.out.println("====================================================================================");
			System.out.println("Assertion Failed");
			System.out.println("====================================================================================");
			
			Assert.assertTrue(state);
		}
			
	}
	
}
