package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class EmailConfirmationPage extends Page {

    private static String URL = "https://stagingapp.celohealth.com/onboard/email-confirm";
    private static String TITLE = "Celo";

    public EmailConfirmationPage(WebDriver driver){
        super(URL, TITLE, driver);
    }
}
