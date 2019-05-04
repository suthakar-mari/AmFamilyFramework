package Steps;

import org.openqa.selenium.WebDriver;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import Driver.ExcelUtils;
import GuicePackage.SimpleModule;
import Pages.CaseInfoFrame;
import Pages.CaseInfoPage;
import Pages.IGoPage;
import Pages.IGoPageFrame;
import Pages.LoginPage;
import Pages.StartPage;

public class Steps {
	
	//private WebDriver driver;
	private Injector injector;
	
    @Inject
	public Steps() throws Exception  {
		injector = Guice.createInjector(new SimpleModule());
		
		//WebDriver driver = injector.getInstance(WebDriver.class);
		//WebDriver newInstanceOfDriver = WebDriverSingleton.getWebDriverInstance();
		//this.driver = driver;
	}

	public void closeDriver() {
		injector.getInstance(WebDriver.class).quit();
	}
	
	public void loginAmFamily(String url, String username) {
		LoginPage loginPage = injector.getInstance(LoginPage.class);
		//LoginPage loginPage = new LoginPage(driver);
		loginPage.signIn(url, username);
	}
	
	public void startNewCase() {
		StartPage startPage = injector.getInstance(StartPage.class);
		//StartPage startPage = new StartPage(driver);
		startPage.startNewCase();
	}
	
	public void switchToCaseInfoFrame() {
		CaseInfoPage caseInfoPage = injector.getInstance(CaseInfoPage.class);
		//CaseInfoPage caseInfoPage = new CaseInfoPage(driver);
		caseInfoPage.switchToCossScreenFrame();
	}
	
	public void fillCaseInfoPage() {		
		CaseInfoFrame caseInfoFrame = injector.getInstance(CaseInfoFrame.class);
		//CaseInfoFrame caseInfoFrame = new CaseInfoFrame(driver);
		caseInfoFrame.fillInPersonalData();
		caseInfoFrame.fillInCaseDescription();
		caseInfoFrame.selectState();
		caseInfoFrame.selectProductType();
		caseInfoFrame.findAvailableProducts();
		caseInfoFrame.openProduct();
	}
	
	public void switchToIGoFrame() {
		IGoPage iGoPage = injector.getInstance(IGoPage.class);
		//IGoPage iGoPage = new IGoPage(driver);
		iGoPage.switchToCossScreenFrame();
	}
	
	public void fillProposedInsured() {		
		IGoPageFrame iGoPageFrame = injector.getInstance(IGoPageFrame.class);
		//IGoPageFrame iGoPageFrame = new IGoPageFrame(driver);
		iGoPageFrame.fillPersonalData();
		iGoPageFrame.fillContactInfo();
	}
	
}
