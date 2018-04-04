package com.appium.automation;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by calcey on 3/7/18.
 */

public class AppiumDriverBase {
    public IOSDriver iosDriver;
    public WebDriverWait wait;
    Set<String> availableContexts;
    ArrayList al = new ArrayList();

    @BeforeTest
    protected void createAppiumDriver() throws MalformedURLException, InterruptedException {
        final File classpathRoot = new File(System.getProperty("user.dir"));
        final File appDir = new File(classpathRoot, "src/test/resources/apps/");
        final File app = new File(appDir, "ApiDemos-debug.apk");

        //setting up desired capability
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", getProperties().getProperty("deviceName"));
        capabilities.setCapability("platformName", getProperties().getProperty("platformName"));
        capabilities.setCapability("platformVersion", getProperties().getProperty("platformVersion"));
        capabilities.setCapability("autoWebView", true);
        capabilities.setCapability("app", getProperties().getProperty("appURL"));
        capabilities.setCapability("useNewWDA", true);

        //initializing driver object
        iosDriver = new IOSDriver(new URL(getProperties().getProperty("appiumURL")), capabilities);

        //initializing explicit wait object
        wait = new WebDriverWait(iosDriver, 30);

        //Changing context
        changeToWebView();
    }

    public IOSDriver getIosDriver() {
        Properties properties = getProperties();
        return iosDriver;
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        try {
            InputStream input = AppiumDriverBase.class.getClassLoader().getResourceAsStream("DriverBase.properties");
            properties.load(input);
            input.close();
        } catch (IOException exception) {

        }
        return properties;
    }

    public void changeToWebView() throws InterruptedException {
        while (iosDriver.getContextHandles() == null) {
            iosDriver.manage().timeouts().wait();
        }
        availableContexts = iosDriver.getContextHandles();
        al.addAll(availableContexts);
        iosDriver.context("" + al.get(1));
    }

    public void changeToNative() {
        availableContexts = iosDriver.getContextHandles();
        al.addAll(availableContexts);
        iosDriver.context("" + al.get(0));
    }

    @AfterTest
    public void afterTest() {
        iosDriver.quit();
    }

}