package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ConversationsPage extends Page{

    private static String URL = "https://stagingapp.celohealth.com/conversations";
    private static String TITLE = "Celo";

    @FindBy(how=How.XPATH,using="//a[contains(@class,'logo')]")
    @CacheLookup
    private WebElement imgLogo;

    @FindBy(how=How.XPATH,using="//a[contains(@href,'conversations/new')]")
    @CacheLookup
    private WebElement linkNewConversation;

    @FindBy(how=How.ID,using="celo-send-message-textarea")
    @CacheLookup
    private WebElement textMessageArea;

    @FindBy(how=How.XPATH, using="//button[contains(@class,'send')]")
    @CacheLookup
    private WebElement btnSend;

    @FindBy(how=How.XPATH,using="//app-message-list")
    @CacheLookup
    private WebElement listMessages;

    @FindBy(how=How.XPATH,using="//div[contains(@class,'standard-message-container-inside')][last()]//div[contains(@class,'my-text')")
    @CacheLookup
    private WebElement textLatestMessage;

    @FindBy(how=How.ID,using="nav-menu")
    @CacheLookup
    private WebElement btnNavMenu;

    private static String LOCK_BUTTON_ID = "celo-lock-button";
    private static String CONVERSATION_CARD_XPATH = "//*[@id='conversations-scroll']//div[normalize-space(text())='";
    private static String MESSAGE_CONTENT_XPATH = "//div[contains(@class,'my-text') and normalize-space(text())='";


    public ConversationsPage(WebDriver driver){
        super(URL,TITLE,driver);
        requiredElements.add(explicitWait().until(ExpectedConditions.visibilityOf(imgLogo)));
        requiredElements.add(explicitWait().until(ExpectedConditions.visibilityOf(linkNewConversation)));
        PageFactory.initElements(driver,this);
    }

    public ConversationsPage openExistingConversation(String name){
        WebElement conversationCard = explicitWait().until(
                ExpectedConditions.visibilityOfElementLocated(makeConversationCardLocatorForName(name)));
        conversationCard.click();
        return this;
    }

    public ConversationsPage sendMessageToCurrentCard(String message){
        textMessageArea.sendKeys(message);
        explicitWait().until(ExpectedConditions.elementToBeClickable(btnSend)).click();
        return this;
    }

    public PinLockPage lockPage(){
        explicitWait().until(ExpectedConditions.elementToBeClickable(btnNavMenu)).click();
        explicitWait().until(ExpectedConditions.elementToBeClickable(makeLockButtonLocator())).click();
        return new PinLockPage(driver());
    }


    public boolean messageVisibility(String message){
        List<WebElement> matchingElements = explicitWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(makeMessageLocator(message)));
        for(WebElement element : matchingElements){
            if(!element.isDisplayed()){
                return false;
            }
        }
        return true;
    }

    private By makeConversationCardLocatorForName(String name){
        String tempXpath = CONVERSATION_CARD_XPATH + name + "']";
        return new By.ByXPath(tempXpath);
    }

    private By makeMessageLocator(String message){
        String tempXpath = MESSAGE_CONTENT_XPATH + message + "']";
        return new By.ByXPath(tempXpath);
    }

    private By makeLockButtonLocator(){
        return new By.ById(LOCK_BUTTON_ID);
    }
}
