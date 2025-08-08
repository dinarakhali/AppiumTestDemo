package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import utils.WaitUtils;

public class AppPage {
    private AndroidDriver driver;
    private By notificationBtn = AppiumBy.accessibilityId("Notification");
    private By incMessage = By.xpath("//android.widget.TextView[@content-desc=\"IncomingMessage\"]");
    private By denyBtn = By.id("com.android.permissioncontroller:id/permission_deny_button");

    public AppPage(AndroidDriver driver) {
        this.driver = driver;
    }

    @Step("Нажимаем на Notification")
    public void clickNotificationBtn() {
        WaitUtils.waitForElementVisible(driver, notificationBtn).click();
    }

    @Step("Нажимаем на Incoming Message")
    public void clickIncMessage() {
        WaitUtils.waitForElementVisible(driver, incMessage).click();
    }

    @Step("Нажимаем 'Отказать' в запросе разрешения")
    public void clickDeny() {
        try {
            WaitUtils.waitForElementVisible(driver, denyBtn).click();
        } catch (TimeoutException e) {
            System.out.println("Окно с разрешением не появилось.");
        }
    }

    @Step("Проверяем отображение кнопки 'Отказать'")
    public boolean isDenyVisible() {
        return WaitUtils.waitForElementVisible(driver, denyBtn).isDisplayed();
    }
}