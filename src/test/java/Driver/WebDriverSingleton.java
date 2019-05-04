package Driver;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;

public class WebDriverSingleton {
	
	private static WebDriver driver;

	private WebDriverSingleton() {	
	}

	public static WebDriver getWebDriverInstance() {
		if (null == driver) {
			driver = createDriver();
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}
		
		return driver;
	}

	public static void closeWebBrowser() {
		driver.quit();
		driver = null;
	}

	private static WebDriver createDriver() {
		WebDriverCreator creator = new ChromeDriverCreator();
		driver = creator.factoryMethod();
		return driver;
	}

}
