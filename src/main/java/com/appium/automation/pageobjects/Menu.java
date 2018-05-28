package com.appium.automation.pageobjects;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

/**
 * Created by calcey on 5/22/18.
 */
public class Menu extends PageBase {

    WebDriverWait wait;
    String faqTitleText = "FAQ";
    String tosTitleText = "USER TERMS AND CONDITIONS";
    String privacyPolicyText = "PRIVACY POLICY";

    By quickPickElement = By.xpath("//div[@class='quick-picks']/ion-label");
    By faqmenu = By.xpath("//div[@class=\"menu-inner\"]/ion-content/div[2]/ion-list/ion-item[5]/div[1]/div/ion-label/button/div[1]/div");
    By faqTitle = By.xpath("//*[@id=\"tabpanel-t0-0\"]/ng-component[2]/ion-content/div[2]/faq-component/div/h1");
    By faqBackbutton = By.xpath("//*[@id=\"tabpanel-t0-0\"]/ng-component[2]/ion-header/ion-navbar/ion-buttons/button/span");
    By termsOfServiceMenu = By.xpath("//div[@class=\"menu-inner\"]/ion-content/div[2]/ion-list/ion-item[6]/div[1]/div/ion-label/button/div[1]/div");
    By termsOfServiceTitle = By.xpath("//*[@id=\"tabpanel-t0-0\"]/ng-component[2]/ion-content/div[2]/terms-and-conditions-component/div/h1");
    By termsOfServiceBackbutton = By.xpath("//*[@id=\"tabpanel-t0-0\"]/ng-component[2]/ion-header/ion-navbar/ion-buttons/button/span");
    By privacyPolicyMenu = By.xpath("//div[@class=\"menu-inner\"]/ion-content/div[2]/ion-list/ion-item[7]/div[1]/div/ion-label/button/div[1]/div");
    By privacyPolicyTitle = By.xpath("//privacy-policy-component/div/h1");


    public Menu(IOSDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, 30);
    }

    public void navigateToFAQPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(quickPickElement));
        swipeHorizontal();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(faqmenu));
        driver.findElement(faqmenu).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(faqTitle));
        Assert.assertEquals(driver.findElement(faqTitle).getText(), faqTitleText);
        driver.findElement(faqBackbutton).click();
    }

    public void navigateToTermsOfServicePage() {
        swipeHorizontal();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if (driver.findElement(termsOfServiceMenu).isDisplayed()) {
            driver.findElement(termsOfServiceMenu).click();
        } else {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            swipeHorizontal();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(termsOfServiceMenu).click();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(termsOfServiceTitle));
        Assert.assertEquals(driver.findElement(termsOfServiceTitle).getText(), tosTitleText);
        wait.until(ExpectedConditions.visibilityOfElementLocated(termsOfServiceBackbutton));
        driver.findElement(termsOfServiceBackbutton).click();
    }

    public void navigateToPrivacyPolicyPage() {
        swipeHorizontal();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if (driver.findElement(privacyPolicyMenu).isDisplayed()) {
            driver.findElement(privacyPolicyMenu).click();
        } else {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            swipeHorizontal();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(privacyPolicyMenu).click();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(privacyPolicyTitle));
        Assert.assertEquals(driver.findElement(privacyPolicyTitle).getText(), privacyPolicyText);
    }
}
