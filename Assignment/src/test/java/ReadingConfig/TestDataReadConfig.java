package ReadingConfig;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestDataReadConfig {
	/**
	 * static Singleton instance.
	 */
	private static TestDataReadConfig instance;
	private String testDataConfigPath = System.getProperty("user.dir")+ "/src/test/resources/com/test/property_files/TestData.properties";
	InputStream input = null;
	Properties prop = null;	

	/**
	 * Private constructor for singleton.
	 * 
	 */
	private TestDataReadConfig(){
	}

	/**
	 * Return a singleton instance of TestDataReadConfig.
	 * 
	 * @throws FileNotFoundException
	 */
	public static TestDataReadConfig getInstance() {
		// Double lock for thread safety.
		if (instance == null) {
			synchronized (TestDataReadConfig.class) {
				if (instance == null) {
					instance = new TestDataReadConfig();
				}
			}
		}
		return instance;
	}

	private void loadPropertyFile() throws IOException {
		input = new FileInputStream(testDataConfigPath);
		prop = new Properties();
		prop.load(input);
	}

	public String readDataConfig(String key) throws IOException {
		loadPropertyFile();
		String value = prop.getProperty(key).trim();
		System.out.println(key +" = "+ value);

		return value;
	}
}
