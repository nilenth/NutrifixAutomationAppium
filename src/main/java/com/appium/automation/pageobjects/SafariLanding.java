package com.appium.automation.pageobjects;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * Created by calcey on 5/3/18.
 */
public class SafariLanding extends SafariBase {

    WebDriverWait wait2;
    Properties properties;


    By emailField = By.xpath("//*[@id='email']");
    By passwordField = By.xpath("//*[@id='password']");
    By loginButton = By.xpath("//button[@class='btn btn-block btn-primary']");

    public SafariLanding(IOSDriver iosDriver2) {
        super(iosDriver2);
        this.wait2 = new WebDriverWait(iosDriver2, 30);
        this.iosDriver2 = iosDriver2;
    }

    public void loginCMS() {
        iosDriver2.findElement(emailField).sendKeys("thahsan@calcey.com");
        iosDriver2.findElement(passwordField).sendKeys("Nutrifix2017!");
        iosDriver2.findElement(loginButton).click();
        iosDriver2.quit();
    }
}