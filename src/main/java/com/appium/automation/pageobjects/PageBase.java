package com.appium.automation.pageobjects;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Dimension;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by calcey on 3/7/18.
 */
public class PageBase {
    public IOSDriver driver;
    private Properties properties;

    public PageBase(IOSDriver driver) {
        this.driver = driver;
        String propertiesPath = getClass().getSimpleName();
        InputStream input = Signup.class.getClassLoader().getResourceAsStream(propertiesPath+".properties");
        properties = new Properties();
        try {
            if (input.available() > 0) {
                properties.load(input);
            }
        } catch (Exception e) {
        }
    }

    public IOSDriver getDriver() {
        return this.driver;
    }

    public Properties getProperties(){
        return this.properties;
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
