package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.WaitUtils;

public class MainPage {
    private AndroidDriver driver;
    private By appBtn = By.xpath("//android.widget.TextView[@content-desc=\'App\']");
    private By viewBtn = AppiumBy.accessibilityId("Views");

    public MainPage(AndroidDriver driver) {
        this.driver = driver;
    }

    @Step("Нажимаем на App")
    public void clickAppBtn() {
        WaitUtils.waitForElementVisible(driver, appBtn).click();
    }

    @Step("Нажимаем на Views")
    public void clickViewBtn() {
        WaitUtils.waitForElementVisible(driver, viewBtn).click();
    }
}