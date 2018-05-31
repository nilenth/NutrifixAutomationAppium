package com.appium.automation.tests;

import com.appium.automation.AppiumDriverBase;
import com.appium.automation.pageobjects.HomePage;
import com.appium.automation.pageobjects.Login;
import com.appium.automation.pageobjects.Signup;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

/**
 * Created by calcey on 5/1/18.
 */
public class LoginTest extends AppiumDriverBase {

    Login login;
    HomePage homePage;

    @BeforeClass(alwaysRun = true)
    public void Setup() throws Exception {
        login = new Login(getIosDriver());
        homePage = new HomePage(getIosDriver());
    }

    @Test(description = "Verify Landing page loaded", priority = 0)
    public void checkLandingPageTest() {
        homePage.checkLandingPage();
    }

    @Test(description = "Verify user navigates to login screen", priority = 1)
    public void navigateToLogin() {
        login.navigateToLoginPage();
    }

    @Test(description = "Verify user logs into the app", priority = 2)
    public void login() {
        login.enterLoginDetails();
    }

    @Test(description = "Verify name of the user logged in is displayed correctly", priority = 3)
    public void verifyLoggedInUserTest() {
        homePage.closeWelcomeMessageAndSwipeHorizontal();
        login.verifyLoggedInUser();
    }

    @Test(description = "Verify that the user can logout from the app", priority = 4)
    public void verifyLogOutTest() {
        homePage.logoutProcess();
        homePage.checkLandingPage();
    }

}