package pages;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

@Getter
@Slf4j
public class MainPage extends BasePage{

    @FindBy(xpath = "//iframe[@id='framelive']")
    private WebElement iframe;

    @FindBy(xpath = "//div[@id='loadingMessage']")
    private WebElement loader;

    @FindBy(xpath = "//footer//div[@class='container']//div[@class='block_newsletter col-lg-8 col-md-12 col-sm-12']")
    private WebElement blockNewsletter;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailFieldForSubscription;

    @FindBy(xpath = "//input[@class='btn btn-primary float-xs-right hidden-xs-down']")
    private WebElement buttonSubscribe;

    @FindBy(xpath = "//p[@class='alert alert-success block_newsletter_alert']")
    private WebElement successSubscriptionMessage;

    //

   @FindBy(xpath = "//button[@class='hidden-sm-down btn-unstyle']")
    private WebElement openDropDown;

   @FindBy(xpath = "//ul[@class='dropdown-menu hidden-sm-down']//li//a[text()='Українська']")
   private WebElement ukrLangItem;




    public MainPage(){
        PageFactory.initElements(getDriver(), this);
    }

    public void oenMainPage(){
        log.info("Opening main page of the web site");
        getDriver().get("https://demo.prestashop.com/");
    }

    public void findIframe(){
        getDriver().switchTo().frame(iframe);
    }

    public boolean waitingUntilLoaderisVisible(){
        try {
            WebDriverWait wait = new WebDriverWait(/*driver*/getDriver(), 10);
            wait.until(ExpectedConditions.visibilityOf(loader));
            return loader.isDisplayed();
        }
        catch (org.openqa.selenium.NoSuchElementException
                | org.openqa.selenium.StaleElementReferenceException
                | org.openqa.selenium.TimeoutException e){
            return false;
        }
    }


    public void scrollToVisibleBlockNewsLetter(){
        JavascriptExecutor js = (JavascriptExecutor) /*driver*/ getDriver();
        js.executeScript("arguments[0].scrollIntoView();", blockNewsletter);
        waitUntilVisible(blockNewsletter, 10);
    }


    public void fillAlreadyUsedEmail(String email){
        waitUntilVisible(blockNewsletter, 10);
        emailFieldForSubscription.sendKeys(email);
        buttonSubscribe.click();
    }


    public String getSuccessSubscriptionMessage(){
         waitUntilVisible(successSubscriptionMessage, 5);
         return successSubscriptionMessage.getText();
    }

    public void openLanguagesDropDownList(){
        waitUntilVisible(openDropDown, 5).click();
    }

    public int checkNumberOfLanguages(){
        List<WebElement> elements = getDriver().findElements(By.xpath("//ul[@class='dropdown-menu hidden-sm-down']//li"));
        return elements.size();
    }

    public String getTextUkrItem(){
        waitUntilVisible(ukrLangItem, 5);
        return ukrLangItem.getText();
    }
}
