import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class InternetTest {

    private WebDriver driver;
    private LoginFactoryPage loginFactoryPage;
    private HomeFactoryPage logoutPageFactory;
    private final static String BASE_URL = "https://the-internet.herokuapp.com";
    private final String USER_NAME = "tomsmith";
    private final String PASSWORD = "SuperSecretPassword!";


    @BeforeMethod
    public void setup() {
        driver = new FirefoxDriver();

        driver.manage().window().maximize();

//        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
//        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
//        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        driver.get(BASE_URL);
        driver.findElement(By.linkText("Form Authentication")).click();
        loginFactoryPage = PageFactory.initElements(driver, LoginFactoryPage.class);
        logoutPageFactory = PageFactory.initElements(driver, HomeFactoryPage.class);



    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void loginTest() {
        loginPage.login(USER_NAME, PASSWORD);
        Assert.assertTrue(driver.findElement(By.cssSelector("#flash.success")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("a[href='/logout']")).isDisplayed());
    }

    @Test
    public void loginFactoryTest() {
        loginFactoryPage.login(USER_NAME, PASSWORD);
//        Assert.assertTrue(driver.findElement(By.cssSelector("#flash.success")).isDisplayed());
//        Assert.assertTrue(driver.findElement(By.cssSelector("a[href='/logout']")).isDisplayed());
        Assert.assertTrue(logoutPageFactory.getFlashPage().isDisplayed());
        Assert.assertTrue(logoutPageFactory.getLogoutButton().isDisplayed());
    }

    @Test
    public void logoutFactoryTest() {
        loginFactoryPage.login(USER_NAME, PASSWORD);
//        Assert.assertTrue(logoutPageFactory.getFlashPage().isDisplayed());
//        Assert.assertTrue(logoutPageFactory.getLogoutButton().isDisplayed());
        logoutPageFactory.logout();
        Assert.assertTrue(loginFactoryPage.getFlashPage().isDisplayed());
        Assert.assertTrue(loginFactoryPage.getLoginButton().isDisplayed());

    }

    @Test
    public void logoutFactoryFlowTest() {
        loginFlowPage
                .login(driver, USER_NAME, PASSWORD)
                .logout(driver)
                .verifyOnLoginPage(driver);
    }

    @Test
    public void uiMapLoginTest() {
        uiMapLoginPage.login(USER_NAME,PASSWORD);
        Assert.assertTrue(driver.findElement(By.cssSelector("#flash.success")).isDisplayed());
    }

    @Test
    public void staticLogin() {
        StaticLoginPage.login(USER_NAME, PASSWORD);
        Assert.assertTrue(driver.findElement(StaticHomePage.FLASH).isDisplayed());
    }
}
