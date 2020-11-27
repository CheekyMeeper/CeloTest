package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PinPage extends Page{

    private static String URL = "https://stagingapp.celohealth.com/pin";
    private static String TITLE = "Celo - Set Pin";

    @FindBy(how= How.ID,using="pin_code")
    @CacheLookup
    private WebElement inputPin;

    @FindBy(how=How.ID,using="pin_code_confirm")
    @CacheLookup
    private WebElement inputPinConfirmation;

    @FindBy(how=How.XPATH,using="//button/span[contains(.,'NEXT')]")
    @CacheLookup
    private WebElement btnNext;

    @FindBy(how= How.XPATH,using="//div[contains(@class,'title') and contains(.,'PIN Error')]")
    @CacheLookup
    private WebElement divPinErrorTitle;

    @FindBy(how= How.XPATH,using="//div[contains(@class,'content') and contains(.,'PIN Error')]")
    @CacheLookup
    private WebElement divPinErrorDescription;

    public PinPage(WebDriver driver){
        super(URL,TITLE,driver);
        requiredElements.add(explicitWait().until(ExpectedConditions.visibilityOf(inputPin)));
        requiredElements.add(explicitWait().until(ExpectedConditions.visibilityOf(inputPinConfirmation)));
        PageFactory.initElements(driver,this);
    }

    public ConversationsPage enterPin(String pin){
        inputPin.sendKeys(pin);
        inputPinConfirmation.sendKeys(pin);
        explicitWait().until(ExpectedConditions.elementToBeClickable(btnNext)).click();
        return new ConversationsPage(driver());
    }

    public String getErrorTitle(){
        return explicitWait().until(ExpectedConditions.visibilityOf(divPinErrorTitle)).getText();
    }
}
