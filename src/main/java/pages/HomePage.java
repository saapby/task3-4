package pages;

import org.openqa.selenium.By;

import static helpers.DriverSingleton.getDriver;

public class HomePage {
    public static final By FLASH = By.cssSelector("#flash.success");
    public static final By LOGOUT_BUTTON = By.cssSelector("a[href='/logout']");

    public static void logout() {
        getDriver().findElement(LOGOUT_BUTTON).click();
    }
}
