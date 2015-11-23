import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomeFactoryPage;
import pages.LoginFactoryPage;
import pages.StaticHomePage;
import pages.StaticLoginPage;

import java.util.concurrent.TimeUnit;

import static helpers.DriverSingleton.getDriver;
import static helpers.DriverSingleton.quit;

public class InternetTest {

    private WebDriver driver;
    private LoginFactoryPage loginFactoryPage;
    private HomeFactoryPage logoutPageFactory;
    private final static String BASE_URL = "https://the-internet.herokuapp.com";
    private final String USER_NAME = "tomsmith";
    private final String PASSWORD = "SuperSecretPassword!";


    @BeforeMethod
    public void setup() {
        getDriver().get(BASE_URL);
        getDriver().manage().window().maximize();

        getDriver().manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        getDriver().manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        loginFactoryPage = PageFactory.initElements(getDriver(), LoginFactoryPage.class);
        logoutPageFactory = PageFactory.initElements(getDriver(), HomeFactoryPage.class);



    }

    @AfterMethod
    public void tearDown() {
        quit();
    }


    @Test
    public void loginFactoryTest() {
        getDriver().findElement(By.linkText("Form Authentication")).click();
        loginFactoryPage.login(USER_NAME, PASSWORD);
        Assert.assertTrue(logoutPageFactory.getFlashPage().isDisplayed());
        Assert.assertTrue(logoutPageFactory.getLogoutButton().isDisplayed());
    }

    @Test
    public void logoutFactoryTest() {
        getDriver().findElement(By.linkText("Form Authentication")).click();
        loginFactoryPage.login(USER_NAME, PASSWORD);
        logoutPageFactory.logout();
        Assert.assertTrue(loginFactoryPage.getFlashPage().isDisplayed());
        Assert.assertTrue(loginFactoryPage.getLoginButton().isDisplayed());

    }

   @Test
    public void staticLogin() {
        getDriver().findElement(By.linkText("Form Authentication")).click();
        StaticLoginPage.login(USER_NAME, PASSWORD);
        Assert.assertTrue(getDriver().findElement(StaticHomePage.FLASH).isDisplayed());



    }

    @Test
    public void staticLogout() {
        getDriver().findElement(By.linkText("Form Authentication")).click();
        StaticLoginPage.login(USER_NAME, PASSWORD);

        Assert.assertTrue(getDriver().findElement(StaticHomePage.FLASH).isDisplayed());

        StaticHomePage.logout();

//        Assert.assertTrue(getDriver().findElement(StaticHomePage.FLASH).isDisplayed());

        //        line for test asserts
//        driver.findElement(By.cssSelector("#username")).sendKeys("tomsmith");
//        driver.findElement(By.cssSelector("#password")).sendKeys("SuperSecretPassword!");

        Assert.assertEquals("http://the-internet.herokuapp.com/login", driver.getCurrentUrl());
        Assert.assertTrue(driver.findElement(By.cssSelector("#flash")).getText().contains("You logged out of the secure area!"), "text is not found, ou are not logged out");

        Assert.assertTrue(driver.findElement(By.cssSelector("input[id='username']")).isDisplayed(), "username field is invisible"); //test
        Assert.assertTrue(driver.findElement(By.cssSelector("input[id='password']")).isDisplayed(), "password field is invisible");

        Assert.assertEquals(driver.findElement(By.cssSelector("input[id='username']")).getAttribute("value"), "", "field username includes text");
        Assert.assertEquals(driver.findElement(By.cssSelector("input[id='password']")).getAttribute("value"), "", "field password includes text");


    }
}
