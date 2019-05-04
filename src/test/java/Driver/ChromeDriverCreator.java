package Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverCreator extends WebDriverCreator {
	
	private static final String CHROME_DRIVER = "chromedriver.exe";
	private static final String CHROME_PROPERTY = "webdriver.chrome.driver";
	
	public WebDriver factoryMethod() {
		System.setProperty(CHROME_PROPERTY, CHROME_DRIVER);
		WebDriver driver = new ChromeDriver();
		return driver;
	}

}
