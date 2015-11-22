package helpers;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Helper {

    public static void check(WebElement checkbox) {
        setCheckboxTo(checkbox, true);
    }

    public static void unCheck(WebElement checkbox) {
        setCheckboxTo(checkbox, false);
    }

    private static void setCheckboxTo(WebElement checkbox, boolean value) {
        if (checkbox.isSelected() != value) {
            checkbox.click();
        }
    }

    public static boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException ex) {
            return false;
        }
    }

    public static List<String> getElementsTexts(Collection<WebElement> webElementsList) {
        List<String > text = new ArrayList<>();
        for (WebElement element: webElementsList) {
            text.add(element.getText());
        }
        return text;
    }
}
