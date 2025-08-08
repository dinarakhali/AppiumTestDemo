package tests;

import io.appium.java_client.android.AndroidDriver;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import pages.AppPage;
import pages.MainPage;
import pages.ViewPage;
import utils.DriverManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {
    private AndroidDriver driver;

    @BeforeEach
    public void setUp() {
        DriverManager.initDriver();
        driver = DriverManager.getDriver();
    }

    @Step("Запуск теста для Alert")
    @Story("Проверка уведомлений")
    @DisplayName("App page Alert Test")
    @Description("Проверяем появление запроса на разрешение уведомлений и его отклонение")
    @Test
    public void testNavigationAlert() {
        MainPage mainPage = new MainPage(driver);
        AppPage appPage = new AppPage(driver);

        mainPage.clickAppBtn();
        appPage.clickNotificationBtn();
        appPage.clickIncMessage();
        assertTrue(appPage.isDenyVisible(), "Запрос на разрешение уведомлений не получен");
        appPage.clickDeny();
    }

    @Step("Запуск теста для Popup")
    @Test
    @Story("Проверка popup-а")
    @DisplayName("View page Popup Test")
    @Description("Проверяем появление появление всплывающего уведомления и его содержимое")
    public void testViewPopup() {
        MainPage mainPage = new MainPage(driver);
        ViewPage viewPage = new ViewPage(driver);

        mainPage.clickViewBtn();
        viewPage.scrollToPopupMenu();
        viewPage.clickPopupMenuBtn();
        viewPage.clickMakePopupBtn();
        viewPage.clickAddBtn();
        assertTrue(viewPage.isPopupVisible(), "Toast popup не появился");
    }

    @Step("Запуск теста для Drag n drop")
    @Test
    @Story("Проверка popup-а")
    @DisplayName("Views Drag and drop test")
    @Description("Проверяем перетаскивание элемента и сообщение об успехе")
    public void testViewDragNDrop() {
        MainPage mainPage = new MainPage(driver);
        ViewPage viewPage = new ViewPage(driver);

        mainPage.clickViewBtn();
        viewPage.clickDragDropBtn();
        viewPage.dragAndDrop();
        assertEquals("Dropped!", viewPage.isResultTextContains(), "Drag n Drop не сработал");
    }

    @AfterEach
    public void tearDown() {
        DriverManager.quitDriver();
    }
}