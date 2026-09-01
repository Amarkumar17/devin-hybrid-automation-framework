package com.framework.retry;

import com.framework.config.ConfigReader;
import com.framework.utils.Log;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;

    @Override
    public boolean retry(ITestResult result) {
        int maxRetryCount = ConfigReader.getInstance().getRetryCount();
        
        if (retryCount < maxRetryCount) {
            retryCount++;
            Log.info("Retrying test " + result.getMethod().getMethodName() + 
                     ", attempt " + retryCount + " of " + maxRetryCount);
            return true;
        }
        Log.info("Test " + result.getMethod().getMethodName() + 
                 " failed after " + maxRetryCount + " attempts");
        return false;
    }

    public void resetCount() {
        retryCount = 0;
    }

    public int getRetryCount() {
        return retryCount;
    }
}
