package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.google.inject.Inject;
import CustomControls.MaskBox;

public class IGoPageFrame extends BasePage{
	
	private CharSequence controlPIPreviousNameValue = "PreviousNameValue";
	private String controlZipValue = "12345-6789";
	
	@FindBy(xpath = "//div[@data-name='PIPreviousName']/div[2]/div/input")
	private WebElement controlPIPreviousName;
	
	@FindBy(xpath = "//div[@data-name='PIADDR_Zip']/div[2]/div/input")
	private MaskBox controlZip;
	
	@Inject
	public IGoPageFrame(WebDriver driverAsInput) {
		super(driverAsInput);
	}
	
	public void fillPersonalData() {
		waitForLoaderAppearAndDisappearedAfterClick();
		controlPIPreviousName.sendKeys(controlPIPreviousNameValue);
	}
	
	public void fillContactInfo() {
		controlZip.sendKeys(controlZipValue);
	}

}
