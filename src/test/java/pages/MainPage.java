package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.WaitUtils;

public class MainPage {
    private AndroidDriver driver;
    private By appBtn = By.xpath("//android.widget.TextView[@content-desc='App']");
    private By viewBtn = AppiumBy.accessibilityId("Views");

    public MainPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void clickAppBtn() {
        WaitUtils.waitForElementVisible(driver, appBtn).click();
    }

    public void clickViewBtn() {
        WaitUtils.waitForElementVisible(driver, viewBtn).click();
    }
}