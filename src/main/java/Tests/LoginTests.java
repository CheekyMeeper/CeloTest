package Tests;

import Constants.Data;
import Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests extends BaseTest {

    @Test
    public void loginWithPin(){
        driver.get(HomePage.BASE_URL);
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.clickLogin();
        // Typically retrieve user credentials from a data source via some kind of reader (file IO or DB connector)
        PinPage pinPage =
                loginPage.loginAs(Data.USERNAME,Data.PASSWORD);
        ConversationsPage conversations = pinPage.enterPin(Data.PIN);
        conversations.openExistingConversation(Data.CARD_NAME);
    }

    @Test
    public void loginWithWeakPin(){
        driver.get(HomePage.BASE_URL);
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.clickLogin();
        // Typically retrieve user credentials from a data source via some kind of reader (file IO or DB connector)
        PinPage pinPage =
                loginPage.loginAs(Data.USERNAME,Data.PASSWORD);
        pinPage.enterPin(Data.WEAK_PIN);
        Assert.assertEquals(pinPage.getErrorTitle(),"PIN Error");
    }

    @Test
    public void loginFromLockedPage(){
        driver.get(HomePage.BASE_URL);
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.clickLogin();
        // Typically retrieve user credentials from a data source via some kind of reader (file IO or DB connector)
        PinPage pinPage =
                loginPage.loginAs(Data.USERNAME,Data.PASSWORD);
        ConversationsPage conversations = pinPage.enterPin(Data.PIN);
        PinLockPage pinLock = conversations.lockPage();
        conversations = pinLock.loginWithPin(Data.PIN);
    }

}
