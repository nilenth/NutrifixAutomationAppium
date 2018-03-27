package com.appium.automation;

import com.appium.automation.pageobjects.PageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import static java.time.Duration.ofMillis;

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
        capabilities.setCapability("deviceName", "iPhone 7 Plus");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "11.2"); //Replace this with your iOS version
        capabilities.setCapability("autoWebView", true);
        capabilities.setCapability("app", "/Users/calcey/Downloads/Nutrifix.app");
        capabilities.setCapability("autoAcceptAlerts", true);
        capabilities.setCapability("useNewWDA", true);

        //initializing driver object
        iosDriver = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);

        //initializing explicit wait object
        wait = new WebDriverWait(iosDriver, 30);

        //Changing context
        changeToWebView();
    }

    public IOSDriver getIosDriver() {
        return iosDriver;
    }

    public void changeToWebView() throws InterruptedException {
        availableContexts = iosDriver.getContextHandles();
        while (availableContexts.isEmpty()) {
            Thread.sleep(10000);
        }
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