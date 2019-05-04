package Pages;

import org.openqa.selenium.WebDriver;

import com.google.inject.Inject;

public class IGoPage extends BasePage{
	
	String IGO_FRAME_ID = "CossScreenFrame";
	
	@Inject
	public IGoPage(WebDriver driverAsInput) {
		super(driverAsInput);
	}
	
	public IGoPageFrame switchToCossScreenFrame() {
		switchToFrame(IGO_FRAME_ID);
		return new IGoPageFrame(getDriver());
	}

}
