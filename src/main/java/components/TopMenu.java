package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TopMenu {
    private static WebDriver driver;
    private static WebDriverWait wait;

    public TopMenu(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }


    //@FindBy(xpath="//a[@title=\"Log in to your customer account\"]")

}
