
package com.bms.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.bms.base.BaseClass;
import com.bms.pages.HomePage;
import com.bms.pages.LoginPage;
import com.bms.utils.ExtentReport;
import com.bms.utils.ListenerUtil;

@Listeners(ListenerUtil.class) 
public class AuthenticationTest extends BaseClass {


    LoginPage loginPage;
    HomePage homePage;
    @BeforeClass
    public void extent() {
    	ExtentReport.createTest("LoginPageTest");
    }
    @BeforeMethod
    public void preparePage() {
        // 1. Navigate to the page
        homePage=new HomePage(driver);
        homePage.goToSignIn();
        // 2. Initialize the object once for this class
        loginPage = new LoginPage(driver);
        
    }

    @Test(priority = 1)
    public void testRegistrationTabVisibility() {
        // Verify registration tab is displayed
        Assert.assertTrue(loginPage.isRegisterTabVisible(), "Registration tab not found!");
    }

    @Test(priority = 2)
    public void testSuccessfulRegistration() {
        // Verify user can register with valid details
        loginPage.registerUser("Aman","aman@test.com", "Secure@123");
        String msg = loginPage.getStatusMessage();
        Assert.assertTrue(msg.toLowerCase().contains("successful"), "Registration failed!");
    }


    @Test(priority = 3)
    public void testInvalidLoginErrorMessage() {
        // Verify error message for unregistered user
        loginPage.loginUser("unknown@user.com", "WrongPass");
        String msg = loginPage.getStatusMessage();
        Assert.assertTrue(msg.toLowerCase().contains("not found"), "Error message not displayed correctly!");
    }

    @Test(priority = 4)
    public void testEmptyFieldsValidation() {
        // Verify behavior when clicking login with empty fields
        loginPage.loginUser("", "");
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Should stay on login page for empty fields");
    }

    @Test(priority = 5)
    public void testSuccessfullLogin(){
        loginPage.loginUser("aman@test.com", "Secure@123");
        String msg = loginPage.getStatusMessage();
        Assert.assertTrue(msg.toLowerCase().contains("successful"), "Login Not Correct!");

    }
}

