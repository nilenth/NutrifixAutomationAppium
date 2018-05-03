package com.appium.automation.tests;

import com.appium.automation.AppiumDriverBase;
import com.appium.automation.pageobjects.HomePage;
import com.appium.automation.pageobjects.Login;
import com.appium.automation.pageobjects.Profile;
import com.appium.automation.pageobjects.Signup;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by calcey on 5/3/18.
 */
public class ProfileTest extends AppiumDriverBase {

    Login login;
    HomePage homePage;
    Profile profile;

    @BeforeClass(alwaysRun = true)
    public void Setup() throws Exception {
        login = new Login(getIosDriver());
        profile = new Profile(getIosDriver());
    }

    @Test(description = "Verify login with a valid user", priority = 0)
    public void loginTest() {
        login.navigateToLoginPage();
        login.enterLoginDetails();
        login.verifyLoggedInUser();
    }

    @Test(description = "Verify user can navigate to the profile page", priority = 1)
    public void navigateToProfilePageTest() {
        profile.navigateToProfilePage();
    }

}
