package com.appium.automation.pageobjects;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by calcey on 5/3/18.
 */
public class Profile extends PageBase {

    WebDriverWait wait;
    Properties properties;
    FileInputStream inputStream;
    String path = System.getProperty("user.dir") + "/src/main/resources/login.properties";
    String newGenderOption;
    String newGenderOptionXpath;
    String newAge;
    String newHeight;
    String newWeight;
    String newActivityOption;
    String newActivityOptionXpath;
    String newGoalOption;
    String newGoalOptionXpath;
    String newDietaryOption;
    String newDietOptionXpath;
    String isDietaryOptionAlreadyChecked;

    By profileMenu = By.xpath("//div[@class=\"menu-inner\"]/ion-content/div[2]/ion-list/ion-item[1]/div[1]/div/ion-label/button/div[1]/div");
    By profileName = By.xpath("//div[@class='nf-profile-header']//h1");
    By ageField = By.xpath("//div[@class='profile-content']/form/div[2]/ion-grid[1]/ion-row[2]/ion-col[2]/input");
    By heightField = By.xpath("//div[@class='profile-content']/form/div[2]/ion-grid[2]/ion-row[2]/ion-col[2]/input");
    By weightField = By.xpath("//div[@class='profile-content']/form/div[2]/ion-grid[2]/ion-row[3]/ion-col[2]/input");
    By updateButton = By.xpath("//div[@class='profile-content']/form/ion-grid/ion-row/ion-col/button");
    By quickPickElement = By.xpath("//div[@class='quick-picks']/ion-label");
    String editedActivityOptionStart = "//div[@class='profile-content']/form/div[2]/div[2]/ion-item[";
    String editedActivityOptionEnd = "]/div[1]/ion-radio/div";
    String editedGoalOptionStart = "//div[@class='profile-content']/form/div[2]/div[3]/ion-item[";
    String editedGoalOptionEnd = "]/div[1]/ion-radio/div";
    String editedGenderOptionStart = "//div[@class='profile-content']/form/div[1]/ion-grid/ion-row[2]/ion-col[";
    String editedGenderOptionEnd = "]/ion-item/div[1]/ion-radio/div";
    String editedDietaryOptionStart = "//div[@class='profile-content']/form/div[2]/div[1]/ion-item[";
    String editedDietaryOptionEnd = "]/ion-checkbox/div";


    public Profile(IOSDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, 30);
        this.driver = driver;
        properties = new Properties();
        try {
            inputStream = new FileInputStream(path);
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void navigateToProfilePage() {
        wait.until(ExpectedConditions.elementToBeClickable(profileMenu));
        driver.findElement(profileMenu).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(profileName));
        Assert.assertEquals(driver.findElement(profileName).getText(), properties.getProperty("userName"));
    }

    public void updateProfile() {
        updateGender();
        updateAge();
        updateHeight();
        updateWeight();
        updateDietaryPreference();
        updateActivity();
        updateGoal();
        updateAllDetails();
    }

    public void updateGender() {
        newGenderOption = createRandomNumber(2, 1);
        newGenderOptionXpath = editedGenderOptionStart + newGenderOption + editedGenderOptionEnd;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newGenderOptionXpath)));
        driver.findElement(By.xpath(newGenderOptionXpath)).click();
    }

    public void updateAge() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ageField));
        driver.findElement(ageField).clear();
        newAge = createRandomNumber(50, 20);
        driver.findElement(ageField).sendKeys(newAge);
    }

    public void updateHeight() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(heightField));
        driver.findElement(heightField).clear();
        newHeight = createRandomNumber(180, 150);
        driver.findElement(heightField).sendKeys(newHeight);
    }

    public void updateWeight() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(weightField));
        driver.findElement(weightField).clear();
        newWeight = createRandomNumber(90, 60);
        driver.findElement(weightField).sendKeys(newWeight);
    }

    public void updateDietaryPreference() {
        newDietaryOption = createRandomNumber(6, 1);
        isDietaryOptionAlreadyChecked = driver.findElement(By.xpath(editedDietaryOptionStart + newDietaryOption + "]/ion-checkbox/button")).getAttribute("aria-checked");
        newDietOptionXpath = editedDietaryOptionStart + newDietaryOption + editedDietaryOptionEnd;
        if (isDietaryOptionAlreadyChecked == "false") {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newDietOptionXpath)));
            driver.findElement(By.xpath(newDietOptionXpath)).click();
        } else {
            System.out.println("Dietary option already selected");
        }
    }

    public void updateActivity() {
        newActivityOption = createRandomNumber(5, 1);
        newActivityOptionXpath = editedActivityOptionStart + newActivityOption + editedActivityOptionEnd;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newActivityOptionXpath)));
        driver.findElement(By.xpath(newActivityOptionXpath)).click();
    }

    public void updateGoal() {
        newGoalOption = createRandomNumber(3, 1);
        newGoalOptionXpath = editedGoalOptionStart + newGoalOption + editedGoalOptionEnd;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newGoalOptionXpath)));
        driver.findElement(By.xpath(newGoalOptionXpath)).click();
    }

    public String createRandomNumber(int maximum, int minimum) {
        String randomnumber = Integer.toString((int) (Math.random() * (maximum - minimum)) + minimum);
        return randomnumber;
    }

    public void updateAllDetails() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(updateButton));
        driver.findElement(updateButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(quickPickElement));
    }

    public void verifyGenderUpdated() {
        swipeHorizontal();
        navigateToProfilePage();
        String isGenderChecked = driver.findElement(By.xpath(editedGenderOptionStart + newGenderOption + "]/ion-item/div[1]/ion-radio/button")).getAttribute("aria-checked");
        Assert.assertTrue(Boolean.valueOf(isGenderChecked));
    }

    public void verifyAgeUpdated() {
        Assert.assertEquals(driver.findElement(ageField).getAttribute("value"), newAge);
    }

    public void verifyWeightUpdated() {
        Assert.assertEquals(driver.findElement(weightField).getAttribute("value"), newWeight);
    }

    public void verifyHeightUpdated() {
        Assert.assertEquals(driver.findElement(heightField).getAttribute("value"), newHeight);
    }

    public void verifyDietaryOptionUpdated() {
        String isDietaryOptionChecked = driver.findElement(By.xpath(editedGenderOptionStart + newGenderOption + "]/ion-item/div[1]/ion-radio/button")).getAttribute("aria-checked");
        Assert.assertTrue(Boolean.valueOf(isDietaryOptionChecked));
    }

    public void verifyActivityUpdated() {
        String isActivityChecked = driver.findElement(By.xpath(editedActivityOptionStart + newActivityOption + "]/div[1]/ion-radio")).getAttribute("ng-reflect-checked");
        Assert.assertTrue(Boolean.valueOf(isActivityChecked));
    }

    public void verifyGoalUpdated() {
        String isGoalChecked = driver.findElement(By.xpath(editedGoalOptionStart + newGoalOption + "]/div[1]/ion-radio")).getAttribute("ng-reflect-checked");
        Assert.assertTrue(Boolean.valueOf(isGoalChecked));
    }

}
