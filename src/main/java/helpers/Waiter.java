package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiter {
    //timeout in seconds
    public void waitForElement(WebDriver driver, int timeOut, final By locator) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
    }

    public void waitForJquery (WebDriver driver, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                JavascriptExecutor ex = (JavascriptExecutor) driver;
                return (Boolean)ex.executeScript("return JQuery.active == 0"); //есть ли активные запросы,
            }
        });
    }

    public void waitForPageLoad(WebDriver driver, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                JavascriptExecutor ex = (JavascriptExecutor) driver;
                return ((String)ex.executeScript("return document.readyState"))
                        .equals("complite");
            }
        });
    }
}
