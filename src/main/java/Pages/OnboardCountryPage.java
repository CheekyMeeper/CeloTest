package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OnboardCountryPage extends Page {

    private static String URL = "https://stagingapp.celohealth.com/onboard/country";
    private static String TITLE = "Celo";

    @FindBy(how=How.ID,using="countryField")
    @CacheLookup
    private WebElement fieldCountry;

    @FindBy(how=How.XPATH,using="//span[@class='mat-button-wrapper' and normalize-space(text())='NEXT']")
    private WebElement btnNext;

    @FindBy(how=How.XPATH,using="//span[@class='mat-button-wrapper' and normalize-space(text())='BACK']")
    @CacheLookup
    private WebElement btnBack;


    public OnboardCountryPage(WebDriver driver){
        super(URL,TITLE,driver);
        PageFactory.initElements(driver,this);
        requiredElements.add(explicitWait().until(ExpectedConditions.elementToBeClickable(fieldCountry)));
        requiredElements.add(explicitWait().until(ExpectedConditions.elementToBeClickable(btnNext)));
        requiredElements.add(explicitWait().until(ExpectedConditions.elementToBeClickable(btnBack)));
    }

    /*
     * Method appears to be flakey due to page timing issues.
     * fieldCountry is found, however sometimes not edited. Fix would include using By instead of PageFactory pattern.
     * This would also reduce the number of explicitWait() calls. However due to time restrictions I have kept this as is.
     */
    public OnboardEmailPage enterCountryAndContinue(String country){
        explicitWait().until(ExpectedConditions.visibilityOf(fieldCountry));
        fieldCountry.sendKeys(country);
        explicitWait().until(ExpectedConditions.textToBePresentInElement(fieldCountry, country));
        btnNext.click();
        return new OnboardEmailPage(driver());
    }

}
