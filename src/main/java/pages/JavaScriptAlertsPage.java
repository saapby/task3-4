package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import static helpers.DriverSingleton.getDriver;

/**
 * Created by saap_by on 23.11.2015.
 */
public class JavaScriptAlertsPage {
    public static final By JS_ALERTS = By.cssSelector("button[onclick='jsAlert()']");
    public static final By JS_CONFIRM = By.cssSelector("button[onclick='jsConfirm()']");
    public static final By JS_PROMPT = By.cssSelector("button[onclick='jsPrompt()']");
    public static final By RESULT = By.id("result");



    public void click(By button) {
        getDriver().findElement(button).click();
    }

    public Alert jsAletr() {
        return getDriver().switchTo().alert();
    }
}
