package org.honestbee;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBaseSetup extends Base {

	@BeforeMethod(alwaysRun = true)
	public void initTest() throws Exception {
		try {
			WebDriver driver;
			try {
				driver = DriverManager.getInstance().getDriver();
			} catch (SessionNotCreatedException s) {
				driver = DriverManager.getInstance().getDriver();
			}
			System.out.println("driver : " + driver);
			driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(240, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
			if (host == null || host.isEmpty()) {
				host = "grid";
			}
		} catch (Exception e) {
			System.out.println("Error in setting up driver");
			e.printStackTrace();
			throw e;
		}
	}

	@AfterMethod(alwaysRun = true)
	public void endTest(ITestResult result) throws IOException {

		if (!result.isSuccess()) {
			StringWriter errors = new StringWriter();
			result.getThrowable().printStackTrace(new PrintWriter(errors));
			String trace = errors.toString();
			System.out.println(trace);
		}

		DriverManager.getInstance().removeDriver();

	}

}
