package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public abstract class Page extends LoadableComponent<Page> {

    protected String url;
    protected String title;

    private WebDriver driver;
    private WebDriverWait explicitWait;
    protected static int MAX_TIMEOUT = 10;
    protected ArrayList<WebElement> requiredElements = new ArrayList<WebElement>();

    public Page(String url, String title, WebDriver driver){
        this.url = url;
        this.title = title;
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        explicitWait = new WebDriverWait(this.driver,MAX_TIMEOUT);
        PageFactory.initElements(driver,this);
    }

    @Override
    protected void load(){
        driver.get(url);
    }

    @Override
    protected void isLoaded(){
        Assert.assertTrue(driver.getCurrentUrl().contains(url));
        isDisplayed();
    }

    public String title(){
        return title;
    }

    public String url(){
        return url;
    }

    protected WebDriver driver(){
        return driver;
    }

    protected WebDriverWait explicitWait(){
        return explicitWait;
    }

    public boolean isDisplayed(){
        boolean result = false;
        try{
            result = explicitWait().until(ExpectedConditions.urlContains(url));
        }catch(Exception e){
            result = false;
        }

        for(WebElement element : requiredElements){
            if(!element.isDisplayed()){
                result = false;
            }
        }
        return result;
    }
}
