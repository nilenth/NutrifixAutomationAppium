package com.appium.automation.tests;

import com.appium.automation.AppiumDriverBase;
import com.appium.automation.pageobjects.HomePage;
import com.appium.automation.pageobjects.Login;
import com.appium.automation.pageobjects.SafariLanding;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

/**
 * Created by calcey on 5/3/18.
 */
public class HomePageTest extends AppiumDriverBase {

    Login login;
    HomePage homePage;
    SafariLanding safariLanding;

    @BeforeClass(alwaysRun = true)
    public void Setup() throws Exception {
        login = new Login(getIosDriver());
        homePage = new HomePage(getIosDriver());
        safariLanding = new SafariLanding(getIosDriver());
    }

    @Test(description = "Verify Landing page loaded", priority = 0)
    public void checkLandingPageTest() {
        login.checkLandingPage();
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

    /*@Test(description = "Verify opening safari browser", priority = 4)
    public void verifyOpenBrowser() throws MalformedURLException {
       // iosDriver.quit();
        safariLanding.openSafari();
    }

    @Test(description = "Verify login to cms", priority = 5)
    public void verifyLoginCMS() {
        safariLanding.loginCMS();
    }

    @Test(description = "Verify going back to Nutrifix", priority = 6)
    public void verifySwitchToNutrifix() throws MalformedURLException, InterruptedException {
        switchToNutrifix();
//        System.out.println(iosDriver.getPageSource());
        //homePage.verifyAppReturn();
        login.checkLandingPage();
        login.navigateToLoginPage();
    }*/
}
