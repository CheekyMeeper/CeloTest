package Tests;

import Constants.Data;
import Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class ConversationTests extends BaseTest {

    @Test
    public void sendMessageToExistingConversation(){
        driver.get(HomePage.BASE_URL);
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.clickLogin();
        // Typically retrieve user credentials from a data source via some kind of reader (file IO or DB connector)
        PinPage pinPage =
                loginPage.loginAs(Data.USERNAME,Data.PASSWORD);
        ConversationsPage conversations = pinPage.enterPin(Data.PIN);
        conversations.openExistingConversation(Data.CARD_NAME);
        conversations.sendMessageToCurrentCard("CeloTest");
        Assert.assertTrue(conversations.messageVisibility("CeloTest"));
    }

}
