package joseph.TestComponents;

import java.io.IOException;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import joseph.Resources.ExtentReporterNG;

public class Listeners1 extends BaseTest implements ITestListener{

	ExtentReports extent=ExtentReporterNG.getReporterObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();//To keep thread safe - avoid overriding of test variable in parallel execution
	
	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		test=extent.createTest(arg0.getMethod().getMethodName());
		extentTest.set(test);//push test in thread local, set gives unique thread ID(creates map of id and test)
	}
	
	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, "Test got passed");
	}
	
	@Override
	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.FAIL, "Test got Failed *****");
		extentTest.get().fail(arg0.getThrowable());
		try {
			driver= (WebDriver) arg0.getTestClass().getRealClass().getField("driver").get(arg0.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		
		String filepath = null;
		try {
			filepath = getScreenshot(arg0.getMethod().getMethodName(),driver);
			getScreenshot(filepath, driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		extentTest.get().addScreenCaptureFromPath(filepath, arg0.getMethod().getMethodName());
		//Take ss and attach to extent reports through test
	}
	


	
	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		extent.flush();
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	


}
