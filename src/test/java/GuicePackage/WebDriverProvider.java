package GuicePackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.inject.Provider;

public class WebDriverProvider implements Provider<WebDriver>{

	private static final String CHROME_DRIVER = "chromedriver.exe";
	private static final String CHROME_PROPERTY = "webdriver.chrome.driver";
	
	public WebDriver get() {
		System.setProperty(CHROME_PROPERTY, CHROME_DRIVER);
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

}
