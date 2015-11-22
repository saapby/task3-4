package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomeFactoryPage {

    @FindBy(css = "#flash.success")
    WebElement flashPage;

    @FindBy(css = "a[href='/logout']")
    WebElement logoutButton;

    public void logout() {
        logoutButton.click();
    }

    public WebElement getFlashPage() {
        return flashPage;
    }

    public WebElement getLogoutButton() {
        return logoutButton;
    }
}
