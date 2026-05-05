package com.bms.utils;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import com.bms.base.BaseClass;

public class ScreenshotUtil extends BaseClass {

    public static void takeScreenshot(String fileName) {
        try {
            File screenshotDir = new File("screenshots/");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdir();
            }

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File("screenshots/" + fileName + ".png");

            FileHandler.copy(src, dest);
            System.out.println("Screenshot saved: " + dest.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getScreenshotPath(String fileName) throws IOException {
        try {
            File screenshotDir = new File("screenshots/");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdir();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File("screenshots/" + fileName + ".png");

        FileHandler.copy(src, dest);
		return dest.getAbsolutePath();
    }
}