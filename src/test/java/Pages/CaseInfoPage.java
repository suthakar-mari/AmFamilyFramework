package Pages;

import org.openqa.selenium.WebDriver;

import com.google.inject.Inject;

public class CaseInfoPage extends BasePage{
	
	String IGO_FRAME_ID = "CossScreenFrame";
	
	@Inject
	public CaseInfoPage(WebDriver driverAsInput) {
		super(driverAsInput);
	}
	
	public CaseInfoFrame switchToCossScreenFrame() {
		switchToFrame(IGO_FRAME_ID);
		return new CaseInfoFrame(getDriver());
	}
}