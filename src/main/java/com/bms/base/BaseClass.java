package com.bms.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.bms.utils.ExtentReport;
import com.bms.utils.ObjectReader;

public class BaseClass {
    public static WebDriver driver;

    @BeforeSuite
    public void setup() {
        // Read browser type from config.properties
    	ExtentReport.setupReport();
        String browserName = ObjectReader.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Read URL from config.properties
        driver.get(ObjectReader.getProperty("url"));
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
        	ExtentReport.flushReport();
            driver.quit();
        }
    }
}