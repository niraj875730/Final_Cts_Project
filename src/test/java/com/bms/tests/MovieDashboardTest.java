package com.bms.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.bms.base.BaseClass;
import com.bms.pages.HomePage;
import com.bms.pages.MoviesPage;
import com.bms.utils.ExtentReport;

public class MovieDashboardTest extends BaseClass {

    MoviesPage moviesPage;
    HomePage homePage;
    
    @BeforeClass
    public void extent() {
    	ExtentReport.createTest("MoviePageTest");
    }
    @BeforeMethod
    public void prepareMoviesPage() {
        // Start from Master Home Page
        homePage = new HomePage(driver);
        homePage.goToMovies();
        // Initialize once for this class
        moviesPage = new MoviesPage(driver);
    }

    @Test(priority = 1)
    public void testTotalMoviesDisplayed() {
        // Verify that all 3 sections combined show at least 12 movies (4 each)
        int count = moviesPage.getTotalMovieCount();
        Assert.assertTrue(count >= 12, "Total movie count is less than expected!");
    }

    @Test(priority = 2)
    public void testSectionNavigationScroll() throws InterruptedException {
        // Verify clicking 'Upcoming' scrolls the page
        moviesPage.clickSectionLink("Upcoming");
        Thread.sleep(1000); // Wait for smooth scroll animation
        Assert.assertTrue(moviesPage.isSectionInViewport("upcoming"), "Page did not scroll to Upcoming section!");
    }

    @Test(priority = 3)
    public void testRecommendedSectionCount() {
        // Verify exactly 4 movies are in the Recommended section
        Assert.assertEquals(moviesPage.getRecommendedMovieCount(), 4, "Recommended section count mismatch!");
    }

    @Test(priority = 4)
    public void testTopRatedRibbonVisibility() {
        // Verify the 'TOP RATED' ribbon exists on the first movie
        Assert.assertTrue(moviesPage.hasTopRatedRibbon(), "Top Rated ribbon is missing!");
    }

    @Test(priority = 5)
    public void testMovieTitleIntegrity() {
        // Verify the first movie title is 'Oppenheimer'
        String title = moviesPage.getFirstMovieTitle();
        Assert.assertEquals(title, "Oppenheimer", "First movie title is incorrect!");
    }
}