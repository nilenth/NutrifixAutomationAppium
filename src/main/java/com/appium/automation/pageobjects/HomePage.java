package com.appium.automation.pageobjects;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by calcey on 5/1/18.
 */
public class HomePage extends PageBase {

    WebDriverWait wait;
    Properties properties;
    String landingPageText = "New user?\nHow can nutrifix help you?";

    By welcomeCloseButton = By.xpath("//button[@class='close-btn disable-hover button button-ios button-default button-default-ios']");
    By mealCard = By.xpath("//div[@class='meal-card']");
    By readMoreButton = By.xpath("//div[@class='card-bot-bar']/ion-row/ion-col[2]/a");
    By paragraph = By.xpath("//div[@class='article-popup']/h2");
    By articleCloseButton = By.xpath("//button[@class='nf-btn-close article disable-hover bar-button bar-button-ios bar-button-clear bar-button-clear-ios']");
    By logOutButton = By.xpath("//button[@class='btn-logout disable-hover button button-ios button-clear button-clear-ios button-block button-block-ios']");
    By landingMessage = By.xpath("//div[@class='login-content purpose']/h2");


    public HomePage(IOSDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, 30);
        this.driver = driver;
    }

    public void closeWelcomeMessageAndSwipeHorizontal() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeCloseButton));
        driver.findElement(welcomeCloseButton).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public void verifyArticleOpen() {
        selectReadMoreBtn();
        wait.until(ExpectedConditions.visibilityOfElementLocated(paragraph));
        swipeVertical();
    }

    public void selectReadMoreBtn() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(readMoreButton));
        driver.findElement(readMoreButton).click();
    }

    public void verifyArticleClose() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(articleCloseButton));
        driver.findElement(articleCloseButton).click();
    }

    public void keepAppInBckground() {
        driver.runAppInBackground(15);
    }

    public void activateNutrifix() {
        driver.launchApp();
       /* driver.activateApp();*/
    }

    public void verifyAppReturn() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(mealCard));
        driver.findElement(articleCloseButton).click();
    }

    public void logoutProcess() {
        wait.until(ExpectedConditions.elementToBeClickable(logOutButton));
        driver.findElement(logOutButton).click();
    }


    public void checkLandingPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(landingMessage));
        Assert.assertEquals(driver.findElement(landingMessage).getText(), landingPageText);
    }

}
