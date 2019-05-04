package GuicePackage;

import org.testng.TestNG;
import Tests.Tests;

public class Runner {

	public static void main(String[] args) {

		TestNG testng = new TestNG();
		testng.setTestClasses(new Class[] { Tests.class });
		testng.run();
	}
}