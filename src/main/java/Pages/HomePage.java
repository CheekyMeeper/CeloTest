package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class HomePage extends Page {

    public static String BASE_URL = "https://stagingapp.celohealth.com/";
    private static String TITLE = "Celo";

    //Elements
    @FindBy(how=How.ID,using="login")
    @CacheLookup
    private WebElement btnLogin;

    @FindBy(how=How.ID,using="signup")
    @CacheLookup
    private WebElement btnSignup;

    public HomePage(WebDriver driver){
        super(BASE_URL,TITLE,driver);
        requiredElements.add(explicitWait().until(ExpectedConditions.visibilityOf(btnLogin)));
        requiredElements.add(explicitWait().until(ExpectedConditions.visibilityOf(btnSignup)));
        PageFactory.initElements(driver,this);
    }

    public LoginPage clickLogin(){
        btnLogin.click();
        return new LoginPage(driver());
    }

    public OnboardCountryPage clickSignUp(){
        btnSignup.click();
        return new OnboardCountryPage(driver());
    }
}
