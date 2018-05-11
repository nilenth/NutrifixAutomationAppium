package com.appium.automation.pageobjects;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * Created by calcey on 5/4/18.
 */
public class SafariBase {
    public IOSDriver iosDriver2;
    private Properties properties;
    String landingPageTitle = "nutrifix-cms";

    public SafariBase(IOSDriver driver) {
    }

    public void openSafari() throws MalformedURLException {
        DesiredCapabilities capabilities2 = new DesiredCapabilities();
        capabilities2.setCapability("deviceName", "iPhone 7 Plus");
        capabilities2.setCapability("platformName", "iOS");
        capabilities2.setCapability("platformVersion", "11.2"); //Replace this with your iOS version
        // capabilities2.setCapability("autoWebView", "true");
        // capabilities2.setCapability("app", "com.apple.mobilesafari");
        capabilities2.setCapability("browserName", "Safari");
        capabilities2.setCapability("autoAcceptAlerts", true);
        capabilities2.setCapability("useNewWDA", true);
        //   capabilities2.setCapability("nativeWebTap",true);
        //   capabilities2.setCapability("noReset", "true");
        iosDriver2 = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities2);
        iosDriver2.get("http://119.235.6.28:8001/");
        Assert.assertEquals(iosDriver2.getTitle(), landingPageTitle);


        /*Set<String> contextHandles = iosDriver.getContextHandles();
        String context = iosDriver.getContext();
        log.println("current" +context);
      //  int i=0;
        iosDriver.context("APPVIEW");
        log.println("current2" +context);*/
    }

}
