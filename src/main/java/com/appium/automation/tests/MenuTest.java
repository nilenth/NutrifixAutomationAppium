package com.appium.automation.tests;

import com.appium.automation.AppiumDriverBase;
import com.appium.automation.pageobjects.Menu;
import com.appium.automation.pageobjects.HomePage;
import com.appium.automation.pageobjects.Login;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by calcey on 5/22/18.
 */
public class MenuTest extends AppiumDriverBase {

    Login login;
    HomePage homePage;
    Menu menu;

    @BeforeClass(alwaysRun = true)
    public void Setup() throws Exception {
        login = new Login(getIosDriver());
        homePage = new HomePage(getIosDriver());
        menu = new Menu(getIosDriver());
    }

    @Test(description = "Verify login with a valid user", priority = 0)
    public void loginTest() {
        login.navigateToLoginPage();
        login.enterLoginDetails();
        homePage.closeWelcomeMessageAndSwipeHorizontal();
        login.verifyLoggedInUser();
    }

    @Test(description = "Verify user navigates to FAQ Page", priority = 1)
    public void checkFAQPageTest() {
        menu.navigateToFAQPage();
    }

    @Test(description = "Verify user navigates to Terms of Service Page", priority = 2)
    public void checkTOSPageTest() {
        menu.navigateToTermsOfServicePage();
    }

    @Test(description = "Verify user navigates to Privacy Policy Page", priority = 3)
    public void checkPrivacyPolicyPageTest() {
        menu.navigateToPrivacyPolicyPage();
    }
}
