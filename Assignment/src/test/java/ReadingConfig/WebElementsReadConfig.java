package ReadingConfig;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebElementsReadConfig {
	/**
	 * static Singleton instance.
	 */
	private String webElementConfigPath = System.getProperty("user.dir")+ "/src/test/resources/com/test/property_files/WebElements.properties";
	InputStream input = null;
	Properties prop = null;
	private static volatile WebElementsReadConfig instance;

	/**
	 * Private constructor for singleton.
	 */
	private WebElementsReadConfig() {
	}

	/**
	 * Return a singleton instance of WebElementsReadConfig.
	 */
	public static WebElementsReadConfig getInstance() {
		// Double lock for thread safety.
		if (instance == null) {
			synchronized (WebElementsReadConfig.class) {
				if (instance == null) {
					instance = new WebElementsReadConfig();
				}
			}
		}
		return instance;
	}
	
	private void loadPropertyFile() throws IOException {
		input = new FileInputStream(webElementConfigPath);
		prop = new Properties();
		prop.load(input);
	}

	public String readWebElementConfig(String key) throws IOException {
		loadPropertyFile();
		String value = prop.getProperty(key).trim();
		System.out.println(key +" = "+ value);

		return value;
	}
}
