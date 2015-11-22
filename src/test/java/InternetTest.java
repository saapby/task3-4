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
       getDriver().manage().window().maximize();

        getDriver().manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        getDriver().manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        getDriver().get(BASE_URL);
        getDriver().findElement(By.linkText("Form Authentication")).click();
        loginFactoryPage = PageFactory.initElements(getDriver(), LoginFactoryPage.class);
        logoutPageFactory = PageFactory.initElements(getDriver(), HomeFactoryPage.class);



    }

    @AfterMethod
    public void tearDown() {
        quit();
    }


    @Test
    public void loginFactoryTest() {
        loginFactoryPage.login(USER_NAME, PASSWORD);
        Assert.assertTrue(logoutPageFactory.getFlashPage().isDisplayed());
        Assert.assertTrue(logoutPageFactory.getLogoutButton().isDisplayed());
    }

    @Test
    public void logoutFactoryTest() {
        loginFactoryPage.login(USER_NAME, PASSWORD);
        logoutPageFactory.logout();
        Assert.assertTrue(loginFactoryPage.getFlashPage().isDisplayed());
        Assert.assertTrue(loginFactoryPage.getLoginButton().isDisplayed());

    }

   @Test
    public void staticLogin() {
        StaticLoginPage.login(USER_NAME, PASSWORD);
        Assert.assertTrue(driver.findElement(StaticHomePage.FLASH).isDisplayed());
    }
}
