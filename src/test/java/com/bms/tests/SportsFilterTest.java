package com.bms.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.bms.base.BaseClass;
import com.bms.pages.HomePage;
import com.bms.pages.SportsPage;
import com.bms.utils.ExtentReport;

public class SportsFilterTest extends BaseClass {

    SportsPage sportsPage;
    HomePage homePage;
    @BeforeClass
    public void extent() {
    	ExtentReport.createTest("SportsPageTest");
    }
    @BeforeMethod
    public void prepareSportsPage() {
        // 1. Start from Home and Navigate to Sports
        homePage = new HomePage(driver);
        homePage.goToSports();
        // 2. Initialize the SportsPage object once for this class
        sportsPage = new SportsPage(driver);
    }

    @Test(priority = 1)
    public void testSportsPageNavigation() {
        //  Verify navigation successful by checking URL
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("sports"), "Navigation to Sports page failed!");
    }

    @Test(priority = 2)
    public void testTotalEventCount() {
        //  Verify total events on initial load is 20
        int initialCount = sportsPage.getCount();
        Assert.assertEquals(initialCount, 20, "Initial event count is incorrect!");
    }

    @Test(priority = 3)
    public void testSearchFunctionality() {
        // Verify search filters the list correctly
        sportsPage.searchFor("Cricket");
        int filteredCount = sportsPage.getCount();
        // Based on our HTML, there are 2 cricket events (Box Cricket and IPL Screening)
        Assert.assertTrue(filteredCount > 0, "Search for 'Cricket' returned no results!");
    }

    @Test(priority = 4)
    public void testWeekendFilter() {
        //Verify 'This Weekend' button filters Saturday/Sunday events
        sportsPage.clickWeekendFilter();
        int weekendCount = sportsPage.getCount();
        Assert.assertTrue(weekendCount < 20, "Weekend filter did not reduce the event count!");
    }

    @Test(priority = 5)
    public void testClearAllFilters() throws InterruptedException {
        //Verify 'Clear All' resets the count back to 20
        sportsPage.searchFor("Marathon");
        Thread.sleep(2000);
        sportsPage.clickClearAll();
        Thread.sleep(2000);

        int resetCount = sportsPage.getCount();
        Assert.assertEquals(resetCount, 20, "Clear All button failed to reset the event list!");
    }

}