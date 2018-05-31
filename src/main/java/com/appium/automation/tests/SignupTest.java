package com.appium.automation.tests;

import com.appium.automation.AppiumDriverBase;
import com.appium.automation.pageobjects.HomePage;
import com.appium.automation.pageobjects.Signup;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by calcey on 3/7/18.
 */
public class SignupTest extends AppiumDriverBase {

    Signup signup;
    HomePage homePage;

    @BeforeClass(alwaysRun = true)
    public void Setup() throws Exception {
        signup = new Signup(getIosDriver());
        homePage = new HomePage(getIosDriver());
    }

    @Test(description = "Verify Home page loaded", priority = 0)
    public void checkLandingPageTest() {
        homePage.checkLandingPage();
    }

    @Test(description = "Verify user selects purpose and navigates to next screen", priority = 1)
    public void selectPurposeTest() {
        signup.selectPurpose();
    }

    @Test(description = "Verify user selects Dietary Preference and navigates to next screen", priority = 2)
    public void selectDietaryPreferenceTest() {
        signup.selectDietaryPreference();
    }

    @Test(description = "Verify user selects Exercise Duration and navigates to next screen", priority = 3)
    public void selectExerciseDurationTest() {
        signup.selectExerciseDuration();
    }

    @Test(description = "Verify metric conversion of height", priority = 4)
    public void heightMetricConversionTest() {
        signup.cmToFtConversion();
        signup.ftToCmConversion();
    }

    @Test(description = "Verify metric conversion of weight", priority = 5)
    public void weightMetricConversionTest() {
        signup.kgToLbsConversion();
        signup.lbsToKgConversion();
    }

    @Test(description = "Verify user enters physical details and navigates to next screen", priority = 6)
    public void enterPhysicalDetailsTest() {
        signup.enterPhysicalDetails();
    }

    @Test(description = "Verify user enters personal details and signs up into the app", priority = 7)
    public void enterSignUpDetailsTest() {
        signup.enterSignUpDetails();
    }

    @Test(description = "Verify name of the user signed up is displayed correctly", priority = 8)
    public void verifySignedUpUserTest() {
        homePage.closeWelcomeMessageAndSwipeHorizontal();
        signup.verifySignedUpUser();
    }

    @Test(description = "Verify that the user can logout from the app", priority = 9)
    public void verifyLogOutTest() {
        homePage.logoutProcess();
        homePage.checkLandingPage();
    }

}
