package com.appium.automation.pageobjects;

import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by calcey on 5/1/18.
 */
public class Login extends PageBase {

    WebDriverWait wait;
    Properties properties;

    String landingPageText = "New user?\nHow can nutrifix help you?";
    String loginTitleText = "Log in";
    String welcomeText = "Welcome to Nutrifix";

    By landingMessage = By.xpath("//div[@class='login-content purpose']/h2");
    By loginOption = By.xpath("//*[@class='link-btn disable-hover button button-ios button-outline button-outline-ios button-block button-block-ios button-outline-ios-text']");
    By loginTitleElement = By.xpath("//*[@class='form ng-untouched ng-pristine ng-invalid']/h2");
    By emailField = By.xpath("//div[@class='login-form']/div/input");
    By passwordField = By.xpath("//div[@class='login-form']/div[2]/input");
    By loginButton = By.xpath("//*[@class='disable-hover button button-ios button-default button-default-ios button-block button-block-ios button-ios-primary']");
    By welcomeElement = By.xpath("//div[@class='tuttorial-content fixed-width margin-top']/h1");
    By userNameElement = By.xpath("//div[@class='profile-header']//h1");
    By welcomeCloseButton = By.xpath("//button[@class='close-btn disable-hover button button-ios button-default button-default-ios']");
    By mealCard = By.xpath("//div[@class='meal-card']");
    By quickPickElement = By.xpath("//div[@class='quick-picks']/ion-label");

    public Login(IOSDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, 30);
        this.driver = driver;
    }

    public void checkLandingPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(landingMessage));
        Assert.assertEquals(driver.findElement(landingMessage).getText(), landingPageText);
    }

    public void checkLandingPage2() {
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(landingMessage));
        Assert.assertEquals(driver.findElement(landingMessage).getText(), landingPageText);
    }

    public void navigateToLoginPage() {
        driver.findElement(loginOption).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginTitleElement));
        Assert.assertEquals(driver.findElement(loginTitleElement).getText(), loginTitleText);
    }

    public void enterLoginDetails() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginTitleElement));
        driver.findElement(emailField).sendKeys(getProperties().getProperty("email"));
        driver.findElement(passwordField).sendKeys(getProperties().getProperty("password"));
        driver.findElement(loginButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeElement));
        Assert.assertEquals(driver.findElement(welcomeElement).getText(), welcomeText);
    }

    public void verifyLoggedInUser() {
        swipeHorizontal();
        verifyUserName();
        swipeHorizontalx();
    }

    public void verifyUserName() {
        if (driver.findElement(userNameElement).isDisplayed()) {
            Assert.assertEquals(driver.findElement(userNameElement).getText(), getProperties().getProperty("userName"));
        } else if (driver.findElement(welcomeCloseButton).isDisplayed()) {
            driver.findElement(welcomeCloseButton).click();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            wait.until(ExpectedConditions.visibilityOfElementLocated(quickPickElement));
            swipeHorizontal();
            wait.until(ExpectedConditions.visibilityOfElementLocated(userNameElement));
            Assert.assertEquals(driver.findElement(userNameElement).getText(), getProperties().getProperty("userName"));
        } else {
            swipeHorizontal();
            wait.until(ExpectedConditions.visibilityOfElementLocated(userNameElement));
            Assert.assertEquals(driver.findElement(userNameElement).getText(), getProperties().getProperty("userName"));
        }
    }
}
