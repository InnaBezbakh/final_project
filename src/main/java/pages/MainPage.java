package pages;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
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

   @FindBy(xpath = "//ul[@class='dropdown-menu hidden-sm-down']//li")
   private List<WebElement> languagesList;

   //@FindBy(xpath = "//ul[@class='dropdown-menu hidden-sm-down']//li")
   //private WebElement langItem;
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
        log.info("Finding and waiting that iframe is visible by locator{}",iframe);
        getDriver().switchTo().frame(iframe);
    }

    public boolean waitingUntilLoaderisVisible(){
        log.info("Finding and waiting until loader is visible by locator{}",loader);
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), 10);
            wait.until(ExpectedConditions.visibilityOf(loader));
            return loader.isDisplayed();
        }
        catch (org.openqa.selenium.NoSuchElementException
                | org.openqa.selenium.StaleElementReferenceException
                | org.openqa.selenium.TimeoutException e){
            return false;
        }
    }


   // public void scrollToVisibleBlockNewsLetter(){
       // JavascriptExecutor js = (JavascriptExecutor) getDriver();
        //js.executeScript("arguments[0].scrollIntoView();", blockNewsletter);
        //waitUntilVisible(blockNewsletter, 10);
    //}
    /*public void scrollToVisibleBlockNewsLetter(WebElement webElement){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", webElement);
        waitUntilVisible(webElement, 10);
    }*/


    public void fillAlreadyUsedEmail(String email){
        waitUntilVisible(blockNewsletter, 10);
        log.info("Finding creation subscription field for email by locator{}",emailFieldForSubscription);
        emailFieldForSubscription.sendKeys(email);
        log.info("Finding and clicking subscribe button by locator{}",buttonSubscribe);
        buttonSubscribe.click();
    }


    public String getSuccessSubscriptionMessage(){
        log.info("success subscription email appears");
         waitUntilVisible(successSubscriptionMessage, 5);
         return successSubscriptionMessage.getText();
    }

    public void openLanguagesDropDownList(){
        waitUntilVisible(openDropDown, 5).click();
    }

    //public int checkNumberOfLanguages(){
      //  List<WebElement> elements = getDriver().findElements(By.xpath("//ul[@class='dropdown-menu hidden-sm-down']//li"));
        //return elements.size();
    //}

    public int checkNumberOfLanguages(){
        log.info("count the number of languages");
        return languagesList.size();
    }

    //public String getTextUkrItem(){
     //   waitUntilVisible(ukrLangItem, 5);
      //  return ukrLangItem.getText();
   // }
    public boolean getTextUkrItem(String language){
        log.info("check whether ukr item exists in the languages list");
        for(int i = 0; i <languagesList.size(); i++){
            if(languagesList.get(i).getText().equals(language)){
                return true;
            }
        }
        return false;
    }

}
