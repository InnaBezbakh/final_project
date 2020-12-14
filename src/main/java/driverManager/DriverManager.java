package driverManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import pages.BasePage;

public class DriverManager {
    // кейс когда сетаплю хром
    //public static synchronized void setUpDriver() {
    //WebDriverManager.chromedriver().setup();
    //WebDriver driver = new ChromeDriver();
    //driver.manage().window().maximize();
    //BasePage.setDriverThreadLocal(driver);
    //}

    public static synchronized void setUpDriver()
    {
        String passedBrowser = System.getProperty("browser");
        WebDriver driver;
        if (passedBrowser==null){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if (passedBrowser.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if (passedBrowser.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else if (passedBrowser.equals("opera")){
            WebDriverManager.operadriver().setup();
            driver = new OperaDriver();
        }
        else{
            throw new IllegalArgumentException("Wrong parametr passed");
        }
        driver.manage().window().maximize();
        BasePage.setDriverThreadLocal(driver);
    }

    public static void quiteDriver() {

        BasePage.getDriver().quit();
    }

}

