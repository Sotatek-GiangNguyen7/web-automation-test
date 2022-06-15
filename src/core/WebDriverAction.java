package core;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class WebDriverAction {
	
    public WebDriver driver;
    public WebElement element;
    
    public WebDriverAction(WebDriver driver) {
        this.driver = driver;
    }
    
    public void goTo(String url) {
        driver.navigate().to(url);
    }
    
    public String getText(By locator) {
        try {
            String var = findElement(locator).getText();
            return var;
        }
        catch (Exception ex) {
            return "0";
        }
    }
    
    public String getLink(By locator) {
        return findElement(locator).getAttribute("href");
    }
    
    public WebElement findElement(By locator) {
        WebElement e = driver.findElement(locator);
        return e;
    }
    
    public void sendKey(By locator, String key) {
        findElement(locator).sendKeys(key);
    }
    
    public void clickElement(By locator) {
        findElement(locator).click();
    }

    
    public void wait(int time) {
    	try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public String getWebsiteName() {
    	return driver.getTitle();
    }
    
    public List<String> elementsToListText(By locator) {
        List<WebElement> list = findElements(locator);
        List<String> strings = new ArrayList<String>();
        for (WebElement e : list) {
            strings.add(e.getText());
        }
        return strings;
    }
    
    public List<WebElement> findElements(By locator){
        List<WebElement> e = driver.findElements(locator);
        return e;
    }

    
}
