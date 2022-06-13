package core;

import java.awt.AWTException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


public class TestSetUp {
	public WebDriver driver;
    @BeforeTest
    public void setUp() throws Exception {
    	driver = WebDriverCreator.createLocalDriver("chrome");
    	driver.get("https://amazon.com");
    }
    @AfterTest
    public void closeTabs() throws AWTException {
    	driver.quit();

    }
}
