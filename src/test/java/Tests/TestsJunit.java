package Tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import Driver.JunitRunner;
import Driver.Utils;
import Steps.Steps;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JunitRunner.class)
public class TestsJunit {

	private static Steps steps;

	@BeforeClass
	static public void setUp() throws Exception {
		steps = new Steps();
	}

	@Test
	public void fillCaseInfoPage() {
		steps.loginAmFamily(Utils.getProperty("BASE_URL"), Utils.getProperty("USERNAME"));
		steps.startNewCase();
		steps.switchToCaseInfoFrame();
		steps.fillCaseInfoPage();
	}

	@Test
	public void fillProposedInsuredPage() {
		steps.switchToIGoFrame();
		steps.fillProposedInsured();
	}

/*	@AfterClass
	public void stopBrowser() {
		steps.closeDriver();
	}*/

}
