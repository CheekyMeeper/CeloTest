package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PinLockPage extends Page {

    @FindBy(how= How.ID,using="pin_code_confirm")
    @CacheLookup
    private WebElement txtPinConfirmation;

    private static String URL = "https://stagingapp.celohealth.com/pin";
    private static String TITLE = "Celo - Pin Lock";

    public PinLockPage(WebDriver driver){
        super(URL,TITLE,driver);
        requiredElements.add(explicitWait().until(ExpectedConditions.elementToBeClickable(txtPinConfirmation)));
        PageFactory.initElements(driver,this);
    }

    public ConversationsPage loginWithPin(String pin){
        explicitWait().until(ExpectedConditions.elementToBeClickable(txtPinConfirmation)).sendKeys(pin);
        return new ConversationsPage(driver());
    }
}
