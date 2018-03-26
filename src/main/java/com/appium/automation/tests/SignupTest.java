package com.appium.automation.tests;

import com.appium.automation.AppiumDriverBase;
//import com.appium.automation.pageobjects.Signup;
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


    @BeforeClass(alwaysRun = true)
    public void Setup() throws Exception {
        signup = new Signup(getIosDriver());
    }

    @Test(description = "Verify Home page loaded", priority = 0)
    public void checkLandingPageTest() {
        signup.checkLandingPage();
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

    @Test(description = "Verify user enters physical details and navigates to next screen", priority = 4)
    public void enterPhysicalDetailsTest() {
        signup.enterPhysicalDetails();
    }

    @Test(description = "Verify user enters personal details and signs up into the app", priority = 5)
    public void enterSignUpDetailsTest() {
        signup.enterSignUpDetails();
    }

    @Test(description = "Verify name of the user signed up is displayed correctly", priority = 6)
    public void verifySignedUpUserTest() {
        signup.closeWelcomeMessage();
        signup.verifySignedUpUser();
    }

    @Test(description = "Verify that the individual article opens correctly", priority = 7)
    public void verifyArticleOpenTest() {
        signup.verifyArticleOpen();
    }

}
