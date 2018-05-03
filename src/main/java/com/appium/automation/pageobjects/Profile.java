package com.appium.automation.pageobjects;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by calcey on 5/3/18.
 */
public class Profile extends PageBase {

    WebDriverWait wait;
    Properties properties;
    FileInputStream inputStream;
    String path = System.getProperty("user.dir") + "/src/main/resources/login.properties";

    By profileMenu = By.xpath("//button[@class='item item-block item-ios']/div[1]/div");
    By profileName = By.xpath("//div[@class='nf-profile-header']//h1");

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
        swipeHorizontal();
        driver.findElement(profileMenu).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(profileName));
        Assert.assertEquals(driver.findElement(profileName).getText(), properties.getProperty("userName"));
    }
}
