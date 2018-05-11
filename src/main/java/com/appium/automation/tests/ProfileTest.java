package com.appium.automation.tests;

import com.appium.automation.AppiumDriverBase;
import com.appium.automation.pageobjects.HomePage;
import com.appium.automation.pageobjects.Login;
import com.appium.automation.pageobjects.Profile;
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
        homePage = new HomePage(getIosDriver());
    }

    @Test(description = "Verify login with a valid user", priority = 0)
    public void loginTest() {
        login.navigateToLoginPage();
        login.enterLoginDetails();
        homePage.closeWelcomeMessageAndSwipeHorizontal();
        login.verifyLoggedInUser();
    }

    @Test(description = "Verify user can navigate to the profile page", priority = 1)
    public void navigateToProfilePageTest() {
        profile.navigateToProfilePage();
    }

    @Test(description = "Verify user can update the profile page", priority = 2)
    public void updateProfileTest() {
        profile.updateProfile();
        profile.navigateToProfilePage();
    }

    @Test(description = "Verify Gender is updated correctly", priority = 3)
    public void verifyGenderUpdate() {
        profile.verifyGenderUpdated();
    }

    @Test(description = "Verify Age is updated correctly", priority = 4)
    public void verifyAgeUpdate() {
        profile.verifyAgeUpdated();
    }

    @Test(description = "Verify height is updated correctly", priority = 5)
    public void verifyHeightUpdate() {
        profile.verifyHeightUpdated();
    }

    @Test(description = "Verify weight is updated correctly", priority = 6)
    public void verifyWeightUpdate() {
        profile.verifyWeightUpdated();
    }

    @Test(description = "Verify Dietary option is updated correctly", priority = 7)
    public void verifyDietaryOptionUpdate() {
        profile.verifyDietaryOptionUpdated();
    }

    @Test(description = "Verify Activity is updated correctly", priority = 8)
    public void verifyActivityUpdate() {
        profile.verifyActivityUpdated();
    }

    @Test(description = "Verify Goal is updated correctly", priority = 9)
    public void verifyGoalUpdate() {
        profile.verifyGoalUpdated();
    }


}
