package Driver;

import org.apache.commons.lang3.time.FastDateFormat;
import org.jfree.data.general.DefaultPieDataset;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomTestListener implements ITestListener {

	private static String DEFAULT_TIMESTAMP_PATTERN = "yyyy-MM-dd_HH-mm-ss";
	private String timeStamp = FastDateFormat.getInstance(DEFAULT_TIMESTAMP_PATTERN).format(System.currentTimeMillis());
	private String Workbook_Name;
	private int RowNum = 1;

	public void onTestStart(ITestResult result) {
		System.out.println("Test is started: " + testName(result) + " at " + timeStamp);

		try {
			ExcelUtils.setCellData(testName(result), Workbook_Name, RowNum, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Test is passed: " + testName(result) + " at " + timeStamp);

		try {
			ExcelUtils.setCellData("Passed", Workbook_Name, RowNum, 1);
			RowNum++;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("Test is failed: " + testName(result) + " at " + timeStamp);
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("Test is skipped: " + testName(result) + " at " + timeStamp);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {

		Workbook_Name = "OutputData_" + timeStamp + ".xlsx";

		try {
			ExcelUtils.createNewWorkbook(Workbook_Name);
			ExcelUtils.setCellData("Test Name", Workbook_Name, 0, 0);
			ExcelUtils.setCellData("Result", Workbook_Name, 0, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onFinish(ITestContext context) {
		System.out.println("Total passed: " + context.getPassedTests().size());
		System.out.println("Total failed: " + context.getFailedTests().size());
		System.out.println("Total skipped: " + context.getSkippedTests().size());

		try {
			
			ExcelUtils.setCellData("Results:", Workbook_Name, 0, 4);
			ExcelUtils.setCellData("Totals:", Workbook_Name, 0, 5);
			ExcelUtils.setCellData("Passed", Workbook_Name, 1, 4);
			ExcelUtils.setCellData(context.getPassedTests().size(), Workbook_Name, 1, 5);
			ExcelUtils.setCellData("Failed", Workbook_Name, 2, 4);
			ExcelUtils.setCellData(context.getFailedTests().size(), Workbook_Name, 2, 5);
			ExcelUtils.setCellData("Skipped", Workbook_Name, 3, 4);
			ExcelUtils.setCellData(context.getSkippedTests().size(), Workbook_Name, 3, 5);

			DefaultPieDataset dataset = new DefaultPieDataset();
			dataset.setValue("Passed", context.getPassedTests().size());
			dataset.setValue("Failed", context.getFailedTests().size());
			dataset.setValue("Skipped", context.getSkippedTests().size());
			ExcelUtils.addPieChartAsPicture(Workbook_Name, dataset);
			ExcelUtils.addLineChart(Workbook_Name, dataset);
			ExcelUtils.addLineChartOnNewSheet(Workbook_Name);
			
			MailUtils.sendMailSSLWithAttachment(Workbook_Name);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String testName(ITestResult result) {
		return result.getInstance().getClass().getSimpleName() + "." + result.getMethod().getMethodName();
	}

}
