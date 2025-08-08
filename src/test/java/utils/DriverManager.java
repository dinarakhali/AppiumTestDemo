package utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.options.SupportsAppOption;
import io.appium.java_client.remote.options.SupportsAutomationNameOption;
import io.appium.java_client.remote.options.SupportsDeviceNameOption;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {

    private static AndroidDriver driver;

    // Метод для инициализации драйвера
    public static void initDriver() {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554");
        options.setPlatformName("Android");
        options.setApp("D:/ApiDemos-debug.apk");
        options.setAutomationName("UiAutomator2");
        options.setNoReset(false);

        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    // Метод для получения текущего драйвера
    public static AndroidDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("Драйвер не инициализирован. Вызовите initDriver()");
        }
        return driver;
    }

    // Метод для закрытия драйвера
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}