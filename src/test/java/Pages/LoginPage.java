package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import Driver.ExcelUtils;

public class LoginPage extends BasePage {
	
	private static String passwordKeysFromProperties;
	private static String usernameKeysFromExcel;
	private static String passwordKeysFromExcel;
	
	@Inject
	public LoginPage(@Named("PASSWORD") String passwordKeys, WebDriver driverAsInput) throws Exception {
		super(driverAsInput);
		LoginPage.passwordKeysFromProperties = passwordKeys;
		LoginPage.usernameKeysFromExcel = ExcelUtils.getCellData(1, 1);
		LoginPage.passwordKeysFromExcel = ExcelUtils.getCellData(1, 2);
		//ExcelUtils.setCellData("Test", 3, 3);
	}
	
	@FindBy(xpath = "//input[@name='user']")
	private WebElement user;
	@FindBy(xpath = "//input[@name='password']")
	private WebElement password;
	@FindBy(xpath = "//button[@name='Submit']")
	private WebElement submit;


	public StartPage signIn(String url, String username) {
		getDriver().get(url);
		user.sendKeys(usernameKeysFromExcel);
		password.sendKeys(passwordKeysFromExcel);
		submit.click();
		return new StartPage(getDriver());
	}

}
