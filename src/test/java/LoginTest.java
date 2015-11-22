import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.StaticHomePage;
import pages.StaticLoginPage;

import static helpers.DriverSingleton.getDriver;
import static helpers.DriverSingleton.quit;


/**
 * Created by Admin on 04.11.2015.
 */
public class LoginTest {

    private StaticLoginPage staticLoginPage;

    private final static String BASE_URL = "http://the-internet.herokuapp.com";
    private final String USER_NAME = "tomsmith";
    private final String PASSWORD = "SuperSecretPassword!";

    @BeforeMethod
    public void setup() {
        getDriver().get(BASE_URL);
        staticLoginPage = new StaticLoginPage();
    }

    @AfterMethod
    public void tearDown() {
        quit();
    }

    @Test
    public void staticLoginTest() {
        StaticLoginPage.login(USER_NAME, PASSWORD);
        Assert.assertTrue(getDriver().findElement(StaticHomePage.FLASH).isDisplayed());
    }
}

//>mvn -Dtest=LoginTest#staticLoginTest test
//"C:\Program Files\Maven\Bin\mvn.exe" -Dtest=LoginTest#staticLoginTest test
