package Tests;

import Constants.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp(){
        //I would typically use a properties file to hold anything like this
        String path = System.getProperty("user.dir");
        System.setProperty(Data.DRIVER_TYPE,
                path+Data.DRIVER_PATH);
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
