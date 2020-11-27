package Tests;

import Constants.Data;
import Pages.*;
import org.testng.annotations.Test;

public class RegisterTests extends BaseTest{

    private String testCountry = "New Zealand";
    private String testEmail = "nic.golding@outlook.com";

    @Test
    public void registerAccount(){
        driver.get(HomePage.BASE_URL);
        HomePage homePage = new HomePage(driver);
        OnboardCountryPage countryPage = homePage.clickSignUp();
        // Typically retrieve user credentials from a data source via some kind of reader (file IO or DB connector)
        OnboardEmailPage emailPage =
                countryPage.enterCountryAndContinue(testCountry);
        EmailConfirmationPage emailConfirmation = emailPage.enterEmailAndContinue(testEmail);
    }

}
