package pages;

import components.TopMenu;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Getter
public abstract class BasePage {

    private TopMenu topMenu;
    public BasePage (){
        this.topMenu=new TopMenu(getDriver());
    }

    private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<WebDriver>();
    public static void setDriverThreadLocal(WebDriver webDriver) {
        DRIVER_THREAD_LOCAL.set(webDriver);
    }

    public static WebDriver getDriver() {
        return DRIVER_THREAD_LOCAL.get();
    }

    public WebElement waitUntilVisible(WebElement element, int time) {
        return new WebDriverWait(getDriver(), time)
                .until(ExpectedConditions.visibilityOf(element));
    }



    public WebElement waitUntilClickable(WebElement element, int time) {
        return new WebDriverWait(getDriver(), time)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public void scrollToVisibleElement(WebElement webElement){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", webElement);
        waitUntilVisible(webElement, 10);
    }

    public boolean waitInvisibilityOf(By locator, int time) {
        return new WebDriverWait(getDriver(), time).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }


}

