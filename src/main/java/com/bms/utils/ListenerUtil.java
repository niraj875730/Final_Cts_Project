package com.bms.utils;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerUtil implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
    	ExtentReport.test.info("TEST PASSED-> " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	ExtentReport.test.info("TEST FAILED-> " + result.getName());
        ScreenshotUtil.takeScreenshot(result.getName());
    }
}