package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 02.11.2015.
 */
public class LoginFactoryPage {

    @FindBy(id = "username")
    WebElement userName;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(css = "button[type='submit']")
    WebElement loginButton;
//    List<WebElement> loginButton;

    @FindBy(css = "#flash.success")
    WebElement flashPage;

    public void login(String user, String pass) {
        userName.sendKeys(user);
        password.sendKeys(pass);
        loginButton.click();
//        loginButton.get(0).click();
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public WebElement getFlashPage() {
        return flashPage;
    }
}
