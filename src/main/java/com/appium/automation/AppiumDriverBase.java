package com.appium.automation;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
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

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * Created by calcey on 3/7/18.
 */

public class AppiumDriverBase {
    public IOSDriver iosDriver;
    public IOSDriver iosDriver3;
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

    public void switchToNutrifix() throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilities3 = new DesiredCapabilities();
        capabilities3.setCapability("deviceName", "iPhone 7 Plus");
        capabilities3.setCapability("platformName", "iOS");
        capabilities3.setCapability("platformVersion", "11.2"); //Replace this with your iOS version
        capabilities3.setCapability("autoWebView", true);
        capabilities3.setCapability("app", getProperties().getProperty("appURL"));
        capabilities3.setCapability("autoAcceptAlerts", true);
        capabilities3.setCapability("useNewWDA", true);
     //   capabilities3.setCapability("noReset", true);
        capabilities3.setCapability("startIWDP",true);
        iosDriver3 = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities3);

        //initializing explicit wait object
        wait = new WebDriverWait(iosDriver3, 30);
        System.out.println(iosDriver3.getContext());
        //Changing context
        //changeToWebView();
        while (iosDriver3.getContextHandles() == null) {
            iosDriver3.manage().timeouts().wait();
        }
        Set<String> availableContexts1 = iosDriver3.getContextHandles();
        ArrayList al = new ArrayList();
        al.addAll(availableContexts1);
        System.out.println("Total No of Context Found Before reaching WebView = " + al.size());
        System.out.println("Context Name is " + availableContexts1);
        iosDriver3.context("" + al.get(1));
        System.out.println(iosDriver3.getContext());

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