package com.appium.automation.pageobjects;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Dimension;

/**
 * Created by calcey on 3/7/18.
 */
public class PageBase {
    public IOSDriver driver;

    public PageBase(IOSDriver driver) {
        this.driver = driver;
    }

    public IOSDriver getDriver() {
        return this.driver;
    }

    public void swipeHorizontal() {
        Dimension size = driver.manage().window().getSize();
        driver.swipe(1, size.height / 2, size.width - 1, size.height / 2, 2000);
    }

    public void swipeHorizontalx() {
        Dimension size = driver.manage().window().getSize();
        driver.swipe(size.width - 1, size.height / 2, 1, size.height / 2, 2000);
    }

    public void swipeVertical() {
        Dimension size = driver.manage().window().getSize();
        driver.swipe(size.width / 2, size.height - 1, size.width / 2, 1, 2000);
    }

}
