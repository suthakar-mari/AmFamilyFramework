package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.google.inject.Inject;

public class StartPage extends BasePage{
	
	@FindBy(xpath = "//a[@id='newcase-button']")
	private WebElement newCaseButton;
	
	@Inject
	public StartPage(WebDriver driverAsInput) {
		super(driverAsInput);
	}

	public CaseInfoPage startNewCase() {
		newCaseButton.click();
		return new CaseInfoPage(getDriver());
	}
	
}
