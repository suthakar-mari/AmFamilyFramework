package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.google.common.base.Predicate;
import com.google.inject.Inject;

import ru.yandex.qatools.htmlelements.element.Select;

public class CaseInfoFrame extends BasePage {
	
	private String firstName = "FirstNameValue";
	private String lastName = "LastNameValue";
	private String caseDescritpion = "Automation";
	private String state = "Iowa";
	private String product = "Fully Underwritten Term";
	private String productType = "10-Year-Level Term";
	private static final String DATEMONTH_XPATH = "//input[contains(@class,'jq-dte-month')]";
	private String dateMonthValue = "11";
	private static final String DATEDAY_XPATH = "//input[contains(@class,'jq-dte-day')]";
	private String dateDayValue = "20";
	private static final String DATEDAY_YEAR = "//input[contains(@class,'jq-dte-year')]";
	private String dateYearValue = "1988";
	private static final By PRODUCT_TYPE_DDL_LOCATOR = By.name("ddlProductType");
	private static final String PRODUCT_XPATH_TEMPLATE = "//td[.='%s']/following-sibling::td/input[contains(@id,'btnIgo')]";

/*	private WebElement txtFirstName;
	private WebElement txtLastName;
	private WebElement txtCaseDescription;
	private Select ddlState;
	private Select ddlProductType;
	private WebElement btnFindAvailableProducts;*/
	
	@FindBy(xpath = "//input[@id='txtFirstName']")
	private WebElement txtFirstName;
	
	@FindBy(xpath = "//input[@id='txtLastName']")
	private WebElement txtLastName;
	
	@FindBy(xpath = DATEMONTH_XPATH)
	private WebElement dateMonthElement;
	
	@FindBy(xpath = DATEDAY_XPATH)
	private WebElement dateDayElement;
	
	@FindBy(xpath = DATEDAY_YEAR)
	private WebElement dateYearElement;
	
	@FindBy(xpath = "//input[@id='txtAge']")
	private WebElement txtAge;
	
	@FindBy(xpath = "//input[@id='txtCaseDescription']")
	private WebElement txtCaseDescription;
	
	@FindBy(xpath = "//select[@id='ddlState']")
	private Select ddlState;
	
	@FindBy(xpath = "//select[@id='ddlProductType']")
	private Select ddlProductType;
	
	@FindBy(xpath = "//intup[@id='btnFindAvailableProducts']")
	private WebElement btnFindAvailableProducts;
	
	@Inject
	public CaseInfoFrame(WebDriver driverAsInput) {
		super(driverAsInput);
	}
	
	public void fillInPersonalData() {

		txtFirstName.sendKeys(firstName);
		txtLastName.sendKeys(lastName);

		if (getDriver().findElement(By.xpath(DATEMONTH_XPATH)).isDisplayed()) {
			dateMonthElement.sendKeys(dateMonthValue);
		}

		if (getDriver().findElement(By.xpath(DATEDAY_XPATH)).isDisplayed()) {
			dateDayElement.sendKeys(dateDayValue);
		}

		if (getDriver().findElement(By.xpath(DATEDAY_YEAR)).isDisplayed()) {
			dateYearElement.sendKeys(dateYearValue);
		}
		
		//waitFor(ExpectedConditions.textToBePresentInElement(txtAge, "28"));
	}
	
	public void fillInCaseDescription() {
		txtCaseDescription.sendKeys(caseDescritpion);
	}
	
	public void selectState() {
		ddlState.selectByVisibleText("Please select...");
		ddlState.selectByVisibleText(state);
	}
	
	public void selectProductType() {
		
		defaultWait().until(new Predicate<WebDriver>() {
			public boolean apply(WebDriver input) {
				return new Select(input.findElement(PRODUCT_TYPE_DDL_LOCATOR)).getOptions().size() > 1;
			}
		});

		ddlProductType.selectByVisibleText("Please select...");
		ddlProductType.selectByVisibleText(product);
	}
	
	public void findAvailableProducts() {
		waitFor(ExpectedConditions.elementToBeClickable(By.id("btnFindAvailableProducts")));
		//waitForMillis(1000);
		getDriver().findElement(By.id("btnFindAvailableProducts")).click();
		//btnFindAvailableProducts.click();
	}
	
	public void openProduct() {
		By productLocator = By.xpath(String.format(PRODUCT_XPATH_TEMPLATE, productType));
		waitFor(ExpectedConditions.presenceOfElementLocated(productLocator));

		getDriver().findElement(productLocator).click();
	}
	
}
