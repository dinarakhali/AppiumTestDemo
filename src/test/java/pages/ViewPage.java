package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import utils.WaitUtils;

import java.time.Duration;
import java.util.List;

public class ViewPage {
    private AndroidDriver driver;
    private By popupMenuBtn = AppiumBy.accessibilityId("Popup Menu");
    private By makePopupBtn = AppiumBy.accessibilityId("Make a Popup!");
    private By addBtn = By.xpath("(//android.widget.LinearLayout[@resource-id='android:id/content'])[2]");
    private By popup = By.xpath("//android.widget.Toast[@text='Clicked popup menu item Add']");
    private By dragDropBtn = AppiumBy.accessibilityId("Drag and Drop");
    private By source = By.xpath("//android.view.View[@resource-id='io.appium.android.apis:id/drag_dot_1']");
    private By target = By.id("io.appium.android.apis:id/drag_dot_2");
    private By resultText = By.id("io.appium.android.apis:id/drag_result_text");

    public ViewPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void scrollToPopupMenu() {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().description(\"Popup Menu\"))"));
    }

    public void clickPopupMenuBtn() {
        WaitUtils.waitForElementVisible(driver, popupMenuBtn).click();
    }

    public void clickMakePopupBtn() {
        WaitUtils.waitForElementVisible(driver, makePopupBtn).click();
    }

    public void clickAddBtn() {
        WaitUtils.waitForElementVisible(driver, addBtn).click();
    }

    public boolean isPopupVisible() {
        try {
            driver.findElement(popup);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickDragDropBtn() {
        WaitUtils.waitForElementVisible(driver, dragDropBtn).click();
    }

    public void dragAndDrop() {
        WebElement sourceWebEl = driver.findElement(source);
        WebElement targetWebEl = driver.findElement(target);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragAndDrop = new Sequence(finger, 1);

        dragAndDrop.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.fromElement(sourceWebEl), 0, 0));
        dragAndDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        dragAndDrop.addAction(finger.createPointerMove(Duration.ofSeconds(1),
                PointerInput.Origin.fromElement(targetWebEl), 0, 0));
        dragAndDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(List.of(dragAndDrop));
    }

    public String isResultTextContains() {
        return driver.findElement(resultText).getText();
    }
}