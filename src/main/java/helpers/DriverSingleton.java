package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class DriverSingleton {
    private static WebDriver driver;
    private DriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            initDriver("default");
        }
        return driver;
    }

    private static void initDriver(String browser) {
        String browserName = System.getProperty("browser", browser);
        switch (browserName) {
            case "chrome": default:
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "ie":
                driver = new InternetExplorerDriver();
                break;
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public static void quit() {
        if (driver != null) {
            driver.quit();
        }
    }
}
