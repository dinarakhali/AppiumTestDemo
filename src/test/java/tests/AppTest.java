package tests;

import io.appium.java_client.android.AndroidDriver;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import pages.AppPage;
import pages.MainPage;
import pages.ViewPage;
import utils.DriverManager;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {
    private AndroidDriver driver;

    @BeforeEach
    public void setUp() {
        DriverManager.initDriver();
        driver = DriverManager.getDriver();
    }

    @Story("Проверка уведомлений")
    @DisplayName("App page Alert Test")
    @Description("Проверяем появление запроса на разрешение уведомлений и его отклонение")
    @Test
    public void testNavigationAlert() {
        MainPage mainPage = new MainPage(driver);
        AppPage appPage = new AppPage(driver);

        step("Нажимаем на App", mainPage::clickAppBtn);
        step("Нажимаем на Notification", appPage::clickNotificationBtn);
        step("Нажимаем на Incoming Message", appPage::clickIncMessage);

        assertTrue(appPage.isDenyVisible(), "Запрос на разрешение уведомлений не получен");
        step("Нажимаем 'Отказать' в запросе разрешения", appPage::clickDeny);
    }

    @Test
    @Story("Проверка popup-а")
    @DisplayName("View page Popup Test")
    @Description("Проверяем появление появление всплывающего уведомления и его содержимое")
    public void testViewPopup() {
        MainPage mainPage = new MainPage(driver);
        ViewPage viewPage = new ViewPage(driver);

        step("Нажимаем на Views", mainPage::clickViewBtn);
        step("Скроллим до Popup Menu", viewPage::scrollToPopupMenu);
        step("Нажимаем на Popup Menu", viewPage::clickPopupMenuBtn);
        step("Нажимаем на Popup", viewPage::clickMakePopupBtn);
        step("Нажимаем на Add", viewPage::clickAddBtn);
        assertTrue(viewPage.isPopupVisible(), "Toast popup не появился");
    }

    @Test
    @Story("Проверка popup-а")
    @DisplayName("Views Drag and drop test")
    @Description("Проверяем перетаскивание элемента и сообщение об успехе")
    public void testViewDragNDrop() {
        MainPage mainPage = new MainPage(driver);
        ViewPage viewPage = new ViewPage(driver);

        mainPage.clickViewBtn();
        step("Нажимаем на Drag and drop", viewPage::clickDragDropBtn);
        step("Перетаскиваем элемент", viewPage::dragAndDrop);
        assertEquals("Dropped!", viewPage.isResultTextContains(), "Drag n Drop не сработал");
    }

    @AfterEach
    public void tearDown() {
        DriverManager.quitDriver();
    }
}