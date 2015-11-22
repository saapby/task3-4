package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static helpers.DriverSingleton.getDriver;

/**
 * Created by Admin on 04.11.2015.
 */
public class StaticLoginPage {
    public static final By USER_NAME_FIELD = By.id("username");
    public static final By PASSWORD_FIELD = By.id("password");
    public static final By LOGIN_BUTTON = By.cssSelector("button[type='submit'");

    public static void login(String user, String pass) {
        getDriver().findElement(USER_NAME_FIELD).sendKeys(user);
        getDriver().findElement(PASSWORD_FIELD).sendKeys(pass);
        getDriver().findElement(LOGIN_BUTTON).click();
    }
}
