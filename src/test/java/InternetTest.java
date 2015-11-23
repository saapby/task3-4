import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.JavaScriptAlertsPage;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

import static helpers.DriverSingleton.getDriver;
import static helpers.DriverSingleton.quit;
import static helpers.Helper.isAlertPresent;

public class InternetTest {

    private WebDriver driver;
    private final static String BASE_URL = "http://the-internet.herokuapp.com";
    private final String USER_NAME = "tomsmith";
    private final String PASSWORD = "SuperSecretPassword!";
    private final String TEXT_ENTER = "any text";

    private LoginPage loginPage;
    private HomePage homePage;
    private JavaScriptAlertsPage javaScriptAlertsPage;


    @BeforeMethod
    public void setup() {
        getDriver().get(BASE_URL);
        getDriver().manage().window().maximize();

        getDriver().manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        getDriver().manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        loginPage = new LoginPage();
        homePage = new HomePage();
        javaScriptAlertsPage = new JavaScriptAlertsPage();
    }

    @AfterMethod
    public void tearDown() {
        quit();
    }

    //TODO Only login
   @Test
    public void staticLoginTest() {
        getDriver().findElement(By.linkText("Form Authentication")).click();
        loginPage.login(USER_NAME, PASSWORD);
        Assert.assertTrue(getDriver().findElement(homePage.FLASH).isDisplayed());
    }

    //TODO login and logout
    @Test
    public void staticLogoutTest() {
        getDriver().findElement(By.linkText("Form Authentication")).click();
        loginPage.login(USER_NAME, PASSWORD);
        Assert.assertTrue(getDriver().findElement(homePage.FLASH).isDisplayed());
        homePage.logout();

//        TODO line for test asserts
//
//        getDriver().findElement(By.cssSelector("#username")).sendKeys(USER_NAME);
//        getDriver().findElement(By.cssSelector("#password")).sendKeys("SuperSecretPassword!");

        Assert.assertEquals("http://the-internet.herokuapp.com/login", getDriver().getCurrentUrl());
        Assert.assertTrue(getDriver().findElement(loginPage.FLASH).getText().contains("You logged out of the secure area!"), "text is not found, ou are not logged out");

        Assert.assertTrue(getDriver().findElement(loginPage.USER_NAME_FIELD).isDisplayed(), "username field is invisible");
        Assert.assertTrue(getDriver().findElement(loginPage.PASSWORD_FIELD).isDisplayed(), "password field is invisible");

        Assert.assertEquals(getDriver().findElement(loginPage.USER_NAME_FIELD).getAttribute("value"), "", "field username includes text"); //test
        Assert.assertEquals(getDriver().findElement(loginPage.PASSWORD_FIELD).getAttribute("value"), "", "field password includes text"); //test
    }

    //TODO JavaScript Alerts jsAlertsTest
    @Test
    public void jsAlertsTest() throws InterruptedException {
        Assert.assertTrue(getDriver().findElement(By.linkText("JavaScript Alerts")).isDisplayed(), "JavaScript Alerts is invisible");

        getDriver().findElement(By.linkText("JavaScript Alerts")).click();

        javaScriptAlertsPage.click(javaScriptAlertsPage.JS_ALERTS);

//        Alert alert = getDriver().switchTo().alert();
        Assert.assertEquals(javaScriptAlertsPage.jsAletr().getText(), "I am a JS Alert");
//        Thread.sleep(3000);
        javaScriptAlertsPage.jsAletr().accept();
        Assert.assertFalse(isAlertPresent(getDriver()), "Alert is present");
//        Thread.sleep(3000);
        Assert.assertEquals(getDriver().findElement(javaScriptAlertsPage.RESULT).getText(), "You successfuly clicked an alert");
    }

    //TODO JavaScript Alerts jsConfirmTest
    @Test
    public void jsConfirmTest() throws InterruptedException {
        Assert.assertTrue(getDriver().findElement(By.linkText("JavaScript Alerts")).isDisplayed(), "JavaScript Alerts is invisible");

        getDriver().findElement(By.linkText("JavaScript Alerts")).click();
        javaScriptAlertsPage.click(javaScriptAlertsPage.JS_CONFIRM);

        Assert.assertEquals(javaScriptAlertsPage.jsAletr().getText(), "I am a JS Confirm");
//        Thread.sleep(3000);

        javaScriptAlertsPage.jsAletr().accept();

        Assert.assertFalse(isAlertPresent(getDriver()), "Alert is present");
//        Thread.sleep(3000);
        Assert.assertEquals(getDriver().findElement(javaScriptAlertsPage.RESULT).getText(), "You clicked: Ok");

        javaScriptAlertsPage.click(javaScriptAlertsPage.JS_CONFIRM);
//        Thread.sleep(3000);
        javaScriptAlertsPage.jsAletr().dismiss();

        Assert.assertFalse(isAlertPresent(getDriver()), "Alert is present");
//        Thread.sleep(3000);
        Assert.assertEquals(getDriver().findElement(javaScriptAlertsPage.RESULT).getText(), "You clicked: Cancel");
    }

    //TODO JavaScript Alerts jsPromptTest
    @Test
    public void jsPromptTest() {
        Assert.assertTrue(getDriver().findElement(By.linkText("JavaScript Alerts")).isDisplayed(), "JavaScript Alerts is invisible");

        getDriver().findElement(By.linkText("JavaScript Alerts")).click();
        javaScriptAlertsPage.click(javaScriptAlertsPage.JS_PROMPT);
        Alert alert = getDriver().switchTo().alert();

        Assert.assertEquals(alert.getText(), "I am a JS Confirm");
//        Thread.sleep(3000);
        alert.sendKeys(TEXT_ENTER);
        alert.accept();




        alert.accept();

        Assert.assertFalse(isAlertPresent(driver), "Alert is present");
        Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: " + TEXT_ENTER);
    }
}
