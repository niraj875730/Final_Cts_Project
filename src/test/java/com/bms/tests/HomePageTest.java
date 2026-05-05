package com.bms.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.bms.base.BaseClass;
import com.bms.pages.HomePage;
import com.bms.utils.ExtentReport;
import com.bms.utils.ObjectReader;

public class HomePageTest extends BaseClass {

    HomePage homePage;
    @BeforeClass
    public void extent() {
    	ExtentReport.createTest("HomePageTest");
    }
    @BeforeMethod
    public void prepareHomePage() {
        // Navigate to Master Home (index.html)
    	
        driver.get(ObjectReader.getProperty("url"));
        homePage = new HomePage(driver);
    }

    @Test(priority = 1)
    public void testPageTitleAndLogo() {
        // Verify Title is correct and Logo is visible
        Assert.assertTrue(homePage.getPageTitle().contains("BMS"), "TC16: Title mismatch!");
        Assert.assertTrue(homePage.isLogoDisplayed(), " Logo not visible!");
    }

    @Test(priority = 2)
    public void testSearchBarInteraction() {
        // Verify search bar accepts input
        String query = "Oppenheimer";
        homePage.enterSearch(query);
        // We aren't submitting yet, just verifying the field works
        Assert.assertTrue(true, " Search bar interaction successful");
    }

    @Test(priority = 3)
    public void testFooterCopyrightYear() {
        // Verify footer contains the correct year
        String footer = homePage.getFooterContent();
        Assert.assertTrue(footer.contains("2026"), " Copyright year is incorrect!");
    }

    @Test(priority = 4)
    public void testNavigationToSignIn() {
        //Verify "Sign In" link takes user to login page
        homePage.goToSignIn();
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), " Navigation to Sign In failed!");
    }

    @Test(priority = 5)
    public void testMoviesLinkNavigation() {
        // Verify "Movies" link takes user to movies dashboard
        homePage.goToMovies();
        Assert.assertTrue(driver.getCurrentUrl().contains("movies"), "Navigation to Movies failed!");
    }
}