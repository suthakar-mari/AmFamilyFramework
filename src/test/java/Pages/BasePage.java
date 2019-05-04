package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

public abstract class BasePage{

	private static final int DEFAULT_WAIT_TIMEOUT_SEC = 1000;
	public static final By LOADER_HIDDEN_LOCATOR = By.xpath("//div[contains(@class,'loading-icon') and contains(@class,'hidden')]");
	public static final By LOADER_LOCATOR = By.xpath("//div[@class='loading-icon']");

	private WebDriver driver;
	private int waitTimeout = DEFAULT_WAIT_TIMEOUT_SEC;
	
	public BasePage(WebDriver driverAsInput) {
		this.driver = driverAsInput;
		HtmlElementLoader.populatePageObject(this, driverAsInput);
	}
	
	protected WebDriver getDriver() {
		return driver;
	}

	public void switchToFrame(String frameId) {
		waitFor(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));
	}

	public void waitForLoaderAppearAndDisappearedAfterClick() {
		waitFor(ExpectedConditions.visibilityOfElementLocated(LOADER_LOCATOR));
		waitFor(ExpectedConditions.presenceOfElementLocated(LOADER_HIDDEN_LOCATOR));
	}

	public void waitFor(ExpectedCondition<?> condition, int timeoutSeconds) {
		new WebDriverWait(driver, timeoutSeconds).until(condition);
	}

	public void waitFor(ExpectedCondition<?> condition) {
		waitFor(condition, getWaitTimeout());
	}
	
	public WebDriverWait wait(int timeoutSeconds) {
		return new WebDriverWait(driver, timeoutSeconds);
	}

	public WebDriverWait defaultWait() {
		return wait(getWaitTimeout());
	}
	
	public void waitForMillis(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int getWaitTimeout() {
		return waitTimeout;
	}

	public void setWaitTimeout(int waitTimeout) {
		this.waitTimeout = waitTimeout;
	}

}
