package testng;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int count = 5;

    @Override
    public boolean retry(ITestResult result) {
        return count-- > 0;
    }
}
