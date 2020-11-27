package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends Page {

    private static String URL = "https://login-staging.celohealth.com/Account/Login";
    private static String TITLE = "Celo Authentication Server";

    // Elements
    @FindBy(how=How.ID,using="Username")
    @CacheLookup
    private WebElement inputUsername;

    @FindBy(how=How.ID, using="Password")
    @CacheLookup
    private WebElement inputPassword;

    @FindBy(how=How.CLASS_NAME,using="btn-login")
    @CacheLookup
    private WebElement btnLogin;

    @FindBy(how=How.LINK_TEXT,using="Forgot Password") // Not ideal, should be changed to something more stable
    @CacheLookup
    private WebElement lnkForgotPassword;

    @FindBy(how=How.LINK_TEXT,using="Get started here") // Not ideal, should be changed to something more stable
    @CacheLookup
    private WebElement lnkGetStarted;


    public LoginPage(WebDriver driver){
        super(URL,TITLE,driver);
        requiredElements.add(explicitWait().until(ExpectedConditions.visibilityOf(inputUsername)));
        requiredElements.add(explicitWait().until(ExpectedConditions.visibilityOf(inputPassword)));
        requiredElements.add(explicitWait().until(ExpectedConditions.visibilityOf(btnLogin)));
        PageFactory.initElements(driver,this);
    }

    public PinPage loginAs(String username, String password){
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);
        btnLogin.click();
        return new PinPage(driver());
    }


}
