package com.appium.automation.pageobjects;

import com.appium.automation.AppiumDriverBase;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.collections.Objects;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by calcey on 3/7/18.
 */
public class Signup extends PageBase {

    WebDriverWait wait;
    String landingPageText = "New user?\nHow can nutrifix help you?";
    String dietaryPreferenceTitleText = "Do you have any dietary preferences?";
    String welcomeText = "Welcome to Nutrifix";
    String quickPickText = "Quick picks";
    String firstName = "jason";


    By landingMessage = By.xpath("//div[@class='login-content purpose']/h2");
    By purposeOption = By.xpath("/html/body/ion-app/ng-component/ion-nav/ng-component/" +
            "ion-content/div[2]/ion-slides/div/div[1]/ion-slide[1]/div/purpose-component/div/ion-list/ion-item[1]/div[1]/div/ion-label/button");
    By dietaryPreferenceTitleElement = By.xpath("//div[@class='login-content']/h2");
    By dietaryPreference = By.xpath("//*[@class='item item-block item-ios item-checkbox']/ion-checkbox");
    By welcomeElement = By.xpath("//div[@class='tuttorial-content fixed-width margin-top']/h1");
    By quickPickElement = By.xpath("//div[@class='quick-picks']/ion-label");
    By welcomeCloseButton = By.xpath("//button[@class='close-btn disable-hover button button-ios button-default button-default-ios']");
    By menubutton = By.xpath("//*[@id='tabpanel-t0-0']/ng-component/ion-header/ion-navbar/ion-buttons/button");
    By userNameElement = By.xpath("//div[@class='profile-header']//h1");
    By readMoreButton = By.xpath("//*[@id=\"tabpanel-t0-0\"]/ng-component/ion-content/div[1]/div/div[2]/home-feed-list-component/div/ion-list/virtual-scroll/div[2]//div[1]/div//div/ion-grid/div/ion-row/ion-col[2]/a");
    By para = By.xpath("/html/body/ion-app/ion-modal/div/ng-component/ion-content/div[2]/div/h2");


    public Signup(IOSDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, 30);
        this.driver = driver;
    }

    public void checkLandingPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(landingMessage));
        Assert.assertEquals(driver.findElement(landingMessage).getText(), landingPageText);
    }

    public void selectPurpose() {
        driver.findElement(purposeOption).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(dietaryPreferenceTitleElement));
        Assert.assertEquals(driver.findElement(dietaryPreferenceTitleElement).getText(), dietaryPreferenceTitleText);
    }

    public void selectDietaryPreference() {
        driver.findElement(dietaryPreference).click();
        driver.findElement(By.xpath("/html/body/ion-app/ng-component/ion-nav/ng-component/ion-footer/div/div/ion-grid/ion-row/ion-col[3]/button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/ion-app/ng-component/ion-nav/ng-component/ion-content/div[2]/ion-slides/div/div[1]/ion-slide[3]/div/exercise-component/div/h2")));
        String text4 = driver.findElement(By.xpath("/html/body/ion-app/ng-component/ion-nav/ng-component/ion-content/div[2]/ion-slides/div/div[1]/ion-slide[3]/div/exercise-component/div/h2")).getText();
        String text5 = "How often do you exercise?";
        Assert.assertEquals(text4, text5);
    }

    public void selectExerciseDuration() {
        driver.findElement(By.xpath("/html/body/ion-app/ng-component/ion-nav/ng-component/ion-footer/div/div/ion-grid/ion-row/ion-col[3]/button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='lbl-22']")));
        String text6 = driver.findElement(By.xpath("/*//*[@id=\"lbl-22\"]")).getText();
        String text7 = "Male";
        Assert.assertEquals(text6, text7);
    }

    public void enterPhysicalDetails() {
        enterGenderAndAge();
        enterHeight();
        enterWeight();
        driver.findElement(By.xpath("/html/body/ion-app/ng-component/ion-nav/ng-component/ion-footer/div/div/ion-grid/ion-row/ion-col[3]/div/button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/ion-app/ng-component/ion-nav/ng-component/ion-content/div[2]/ion-slides/div/div[1]/ion-slide[5]/div/signup-component/form/div/h2")));
        String text8 = driver.findElement(By.xpath("/html/body/ion-app/ng-component/ion-nav/ng-component/ion-content/div[2]/ion-slides/div/div[1]/ion-slide[5]/div/signup-component/form/div/h2")).getText();
        String text9 = "Register your account to save your settings";
        Assert.assertEquals(text8, text9);

    }

    public void enterSignUpDetails() {
        driver.findElement(By.xpath("//*[@id='firstName']")).sendKeys(firstName);
        String randomNumbers = RandomStringUtils.randomNumeric(5);
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(firstName + randomNumbers + "@mailinator.com");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("user@123");
        driver.findElement(By.xpath("/html/body/ion-app/ng-component/ion-nav/ng-component/ion-content/div[2]/ion-slides/div/div[1]/ion-slide[5]/div/signup-component/form/div/div[3]/ion-grid/ion-row[2]/ion-col[1]/ion-checkbox")).click();
        driver.findElement(By.xpath("/html/body/ion-app/ng-component/ion-nav/ng-component/ion-footer/div/div/ion-grid/ion-row/ion-col[3]/button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeElement));
        Assert.assertEquals(driver.findElement(welcomeElement).getText(), welcomeText);
    }

    public void verifySignedUpUser() {
        swipeHorizontal();
        verifyUserName();
        swipeHorizontalx();
    }

    public void verifyArticleOpen() {
        selectReadMoreBtn();
        wait.until(ExpectedConditions.visibilityOfElementLocated(para));
        swipeVertical();

    }

    public void enterGenderAndAge() {
        driver.findElement(By.xpath("//*[@id=\"rb-22-0\"]")).click();
        driver.findElement(By.xpath("/html/body/ion-app/ng-component/ion-nav/ng-component/ion-content/div[2]/ion-slides/div/div[1]/ion-slide[4]/div/physical-component/div/form/div[2]/ion-grid/ion-row[1]/ion-col[2]/input")).sendKeys("25");
    }

    public void enterHeight() {
        driver.findElement(By.xpath("//*[@id=\"height\"]")).sendKeys("175");
    }

    public void enterWeight() {
        driver.findElement(By.xpath("//*[@id=\"weight\"]")).sendKeys("75");
    }

    public void closeWelcomeMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeCloseButton));
        driver.findElement(welcomeCloseButton).click();
    }

    public void verifyUserName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameElement));
        Assert.assertEquals(driver.findElement(userNameElement).getText(), firstName);
    }

    public void selectReadMoreBtn() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(readMoreButton));
        driver.findElement(readMoreButton).click();
    }

}

