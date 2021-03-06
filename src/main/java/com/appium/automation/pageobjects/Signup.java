package com.appium.automation.pageobjects;

import com.appium.automation.AppiumDriverBase;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.util.TextUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.collections.Objects;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by calcey on 3/7/18.
 */
public class Signup extends PageBase {

    WebDriverWait wait;
    Properties properties;
    String dietaryPreferenceTitleText = "Do you have any dietary preferences?";
    String welcomeText = "Welcome to Nutrifix";
    String quickPickText = "Quick picks";
    String exercisePageTitleText = "How often do you exercise?";
    String signupPageTitleText = "Register your account to save your settings";
    double dCentimeter;


    By purposeOption = By.xpath("//button[@type='button' and contains(., 'Be healthier')]");
    By dietaryPreferenceTitleElement = By.xpath("//div[@class='login-content']/h2");
    By dietaryPreference = By.xpath("//*[@class='item item-block item-ios item-checkbox']/ion-checkbox");
    By continueButton = By.xpath("//span[contains(text(),'Continue')]");
    By exercisePageTitleElement = By.xpath("//exercise-component/div[@class='login-content']/h2");
    By welcomeElement = By.xpath("//div[@class='tuttorial-content fixed-width margin-top']/h1");
    By genderElement = By.xpath("//*[@id='lbl-22']");
    By maleGenderOption = By.xpath("//*[@id=\"rb-22-0\"]");
    By ageField = By.xpath("//div[@class='user-details']/ion-grid/ion-row[1]/ion-col[2]/input");
    By heightField = By.xpath("//*[@id=\"height\"]");
    By weightField = By.xpath("//*[@id=\"weight\"]");
    By registerButton = By.xpath("//span[contains(text(),'Register')]");
    By firstNameField = By.xpath("//*[@id='firstName']");
    By emailField = By.xpath("//*[@id=\"email\"]");
    By passwordField = By.xpath("//*[@id=\"password\"]");
    By signupTitleElement = By.xpath("//*[@class='signup-title']");
    By agreementCheckbox = By.xpath("//*[@class='checkbox checkbox-ios checkbox-ios-primary ng-untouched ng-pristine ng-invalid']");
    By signupButton = By.xpath("//span[contains(text(),'Sign Up')]");
    By quickPickElement = By.xpath("//div[@class='quick-picks']/ion-label");
    By menuButton = By.xpath("//*[@id='tabpanel-t0-0']/ng-component/ion-header/ion-navbar/ion-buttons/button");
    By userNameElement = By.xpath("//div[@class='profile-header']//h1");
    By articleTitle = By.xpath("//div[@class='scrollable-content']/ion-item[1]");
    By welcomeCloseButton = By.xpath("//button[@class='close-btn disable-hover button button-ios button-default button-default-ios']");
    By mealCard = By.xpath("//div[@class='meal-card']");
    By cmToFtToggle = By.xpath("//span[contains(text(),'cm')]");
    By kgToLbsToggle = By.xpath("//span[contains(text(),'kg')]");


    public Signup(IOSDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, 30);
        this.driver = driver;
    }

    public void selectPurpose() {
        driver.findElement(purposeOption).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(dietaryPreferenceTitleElement));
        Assert.assertEquals(driver.findElement(dietaryPreferenceTitleElement).getText(), dietaryPreferenceTitleText);
    }

    public void selectDietaryPreference() {
        driver.findElement(dietaryPreference).click();
        driver.findElement(continueButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(exercisePageTitleElement));
        Assert.assertEquals(driver.findElement(exercisePageTitleElement).getText(), exercisePageTitleText);
    }

    public void selectExerciseDuration() {
        driver.findElement(continueButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(genderElement));
        Assert.assertEquals(driver.findElement(genderElement).getText(), getProperties().getProperty("gender"));
    }

    public void enterPhysicalDetails() {
        enterGenderAndAge();
        driver.findElement(registerButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(signupTitleElement));
        Assert.assertEquals(driver.findElement(signupTitleElement).getText(), signupPageTitleText);
    }

    public void enterSignUpDetails() {
        driver.findElement(firstNameField).sendKeys(getProperties().getProperty("firstName"));
        String randomNumbers = RandomStringUtils.randomNumeric(5);
        driver.findElement(emailField).sendKeys(getProperties().getProperty("firstName") + randomNumbers + "@dispostable.com");
        driver.findElement(passwordField).sendKeys(getProperties().getProperty("password"));
        driver.findElement(agreementCheckbox).click();
        driver.findElement(signupButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeElement));
        Assert.assertEquals(driver.findElement(welcomeElement).getText(), welcomeText);
    }

    public void verifySignedUpUser() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        swipeHorizontal();
        verifyUserName();
    }

    public void enterGenderAndAge() {
        driver.findElement(maleGenderOption).click();
        driver.findElement(ageField).sendKeys(getProperties().getProperty("age"));
    }

    public void cmToFtConversion() {
        driver.findElement(heightField).sendKeys(getProperties().getProperty("height"));
        driver.findElement(cmToFtToggle).click();
        double dCentimeter = Double.valueOf(getProperties().getProperty("height"));
        int feetPart = (int) Math.floor((dCentimeter / 2.54) / 12);
        int inchesPart = (int) Math.round((dCentimeter / 2.54) - (feetPart * 12));
        Assert.assertEquals(driver.findElement(heightField).getAttribute("value"), String.format("%d' %d\"", feetPart, inchesPart));
    }

    public void ftToCmConversion() {
        String feetValue = driver.findElement(heightField).getAttribute("value");
        if (feetValue.contains("'")) {
            String tempfeet = feetValue.substring(0, feetValue.indexOf("'"));
            if (!TextUtils.isEmpty(tempfeet)) {
                dCentimeter += ((Double.valueOf(tempfeet)) * 30.48);
            }
        }
        if (feetValue.contains("\"")) {
            String tempinch = feetValue.substring(feetValue.indexOf("'") + 1, feetValue.indexOf("\""));
            if (!TextUtils.isEmpty(tempinch)) {
                dCentimeter += ((Double.valueOf(tempinch)) * 2.54);
            }
        }
        driver.findElement(cmToFtToggle).click();
        Assert.assertEquals(driver.findElement(heightField).getAttribute("value"), Double.toString(dCentimeter));
    }

    public void kgToLbsConversion() {
        driver.findElement(weightField).sendKeys(getProperties().getProperty("weight"));
        driver.findElement(kgToLbsToggle).click();
        double kgs = Double.valueOf(getProperties().getProperty("weight"));
        double lbs = kgs * 2.20462;
        Assert.assertEquals(driver.findElement(weightField).getAttribute("value"), String.format("%.2f", lbs));
    }

    public void lbsToKgConversion() {
        double lbs = Double.valueOf(driver.findElement(weightField).getAttribute("value"));
        double kgs = lbs / 2.20462;
        driver.findElement(kgToLbsToggle).click();
        Assert.assertEquals(driver.findElement(weightField).getAttribute("value"), String.format("%.2f", kgs));
    }

    public void verifyUserName() {
        if (driver.findElement(userNameElement).isDisplayed()) {
            Assert.assertEquals(driver.findElement(userNameElement).getText(), getProperties().getProperty("firstName"));
        } else if (driver.findElement(welcomeCloseButton).isDisplayed()) {
            driver.findElement(welcomeCloseButton).click();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            wait.until(ExpectedConditions.visibilityOfElementLocated(mealCard));
            swipeHorizontal();
            wait.until(ExpectedConditions.visibilityOfElementLocated(userNameElement));
            Assert.assertEquals(driver.findElement(userNameElement).getText(), getProperties().getProperty("firstName"));
        } else {
            swipeHorizontal();
            wait.until(ExpectedConditions.visibilityOfElementLocated(userNameElement));
            Assert.assertEquals(driver.findElement(userNameElement).getText(), getProperties().getProperty("firstName"));
        }
    }
}

