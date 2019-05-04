package Driver;

import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class JUnitExecutionListener extends RunListener {
	
	private static String DEFAULT_TIMESTAMP_PATTERN = "yyyy-MM-dd_HH-mm-ss";
	private String timeStamp = FastDateFormat.getInstance(DEFAULT_TIMESTAMP_PATTERN).format(System.currentTimeMillis());
	private String Workbook_Name;
	private int RowNum = 1;

	@Override
	public void testAssumptionFailure(Failure failure) {
		// TODO Auto-generated method stub
		super.testAssumptionFailure(failure);
	}

	@Override
	public void testFailure(Failure failure) throws Exception {
		System.out.println("Test is failed: " + testName(failure) + " at " + timeStamp);
		super.testFailure(failure);
	}

	@Override
	public void testFinished(Description description) throws Exception {
		// TODO Auto-generated method stub
		super.testFinished(description);
	}

	@Override
	public void testIgnored(Description description) throws Exception {
		// TODO Auto-generated method stub
		super.testIgnored(description);
	}

	@Override
	public void testRunFinished(Result result) throws Exception {
		// TODO Auto-generated method stub
		super.testRunFinished(result);
	}

	@Override
	public void testRunStarted(Description description) throws Exception {
		// TODO Auto-generated method stub
		super.testRunStarted(description);
	}

	@Override
	public void testStarted(Description description) throws Exception {
		System.out.println("Test is started: " + testName(description) + " at " + timeStamp);
		super.testStarted(description);
	}
	
	private String testName(Description description) {
		return description.getClassName() + "." + description.getMethodName();
	}
	
	private String testName(Failure failure) {
		return failure.getClass().getName() + "." + failure.getClass().getMethods();
	}
	
}
