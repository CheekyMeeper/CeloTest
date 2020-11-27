package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OnboardEmailPage extends Page{

    private static String URL = "https://stagingapp.celohealth.com/onboard/email";
    private static String TITLE = "Celo";

    @FindBy(how= How.ID,using="emailField")
    @CacheLookup
    private WebElement textEmailField;

    @FindBy(how=How.XPATH,using="//button/span[normalize-space(text())='NEXT']")
    @CacheLookup
    private WebElement btnNext;

    @FindBy(how=How.XPATH,using="//button/span[normalize-space(text())='BACK']")
    @CacheLookup
    private WebElement btnBack;

    public OnboardEmailPage(WebDriver driver){
        super(URL,TITLE,driver);
        requiredElements.add(explicitWait().until(ExpectedConditions.visibilityOf(textEmailField)));
        requiredElements.add(explicitWait().until(ExpectedConditions.visibilityOf(btnNext)));
        requiredElements.add(explicitWait().until(ExpectedConditions.visibilityOf(btnBack)));
        PageFactory.initElements(driver,this);
    }

    public EmailConfirmationPage enterEmailAndContinue(String email){
        textEmailField.sendKeys(email);
        explicitWait().until(ExpectedConditions.elementToBeClickable(btnNext)).click();
        return new EmailConfirmationPage(driver());
    }
}
