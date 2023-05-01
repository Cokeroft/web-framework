package helpers;

import org.openqa.selenium.WebDriver;

public class LocalDriverManager {
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    private static String url;


    public static WebDriver getDriver() {
        return webDriver.get();
    }

    public static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        LocalDriverManager.url = url;
    }
}