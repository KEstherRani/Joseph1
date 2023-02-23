package joseph.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFM implements IRetryAnalyzer {

	int count=0;
	int maxTry=1;
	
	@Override
	public boolean retry(ITestResult arg0) {
		// TODO Auto-generated method stub
		if(count<maxTry){
			count++;
			return true;
		}
		
		return false;
	}

}
