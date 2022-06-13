package main.pageobject.amazon;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import core.WebDriverAction;
import main.dto.ProductDataObject;
import main.pageobject.common.SortByPrice;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.google.gson.Gson;

public class AmazonWebsitePage extends WebDriverAction {
	By searchBox = By.xpath("//input[@id=\"twotabsearchtextbox\"]");
	By searchButton = By.xpath("//input[@id='nav-search-submit-button']");
	
	String productLink = "//div[@class=\"s-main-slot s-result-list s-search-results sg-row\"]/div[@data-component-type=\"s-search-result\"]{0}//a[@class=\"a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal\"]";
	String productName = "//div[@class=\"s-main-slot s-result-list s-search-results sg-row\"]/div[@data-component-type=\"s-search-result\"]{0}//span[@class=\"a-size-medium a-color-base a-text-normal\"]";
	String stringProductPriceWhole = "//div[@class=\"s-main-slot s-result-list s-search-results sg-row\"]/div[@data-component-type=\"s-search-result\"]{0}//span[@class=\"a-price\"]//span[@class=\"a-price-whole\"]";
	String stringProductPriceFraction = "//div[@class=\"s-main-slot s-result-list s-search-results sg-row\"]/div[@data-component-type=\"s-search-result\"]{0}//span[@class=\"a-price\"]//span[@class=\"a-price-fraction\"]";
	public AmazonWebsitePage(WebDriver driver) {
		super(driver);
	}
	
	public void searchProduct(String key) {
		sendKey(searchBox, key);
	}
	
	public void clickButtonSearch() {
		clickElement(searchButton);
	}
	
    public ArrayList<ProductDataObject> getProductList() {
    	ArrayList<ProductDataObject> list = new ArrayList<ProductDataObject>();
        for (int x = 1; x <= nameList().size(); x++){
        	ProductDataObject productDataObject = new ProductDataObject();
        	productDataObject.websiteName = getWebsiteName();
			productDataObject.productName = productNameByIndex(x);
			productDataObject.productPrice = productPriceByIndex(x);
			productDataObject.productLink = productLinkByIndex(x);
			list.add(productDataObject);
        }
		return list;
    	
    }
    
    public List<String> nameList(){
        String ProductName = MessageFormat.format(productName, "");
        return elementsToListText(By.xpath(ProductName));
    }
    
    public String productLinkByIndex(int index) {
        String ProductLink = MessageFormat.format(productLink, "[" + index + "]");
        return getLink(By.xpath(ProductLink));
    }
    
    public String productNameByIndex(int index) {
        String ProductName = MessageFormat.format(productName, "[" + index + "]");
        return getText(By.xpath(ProductName));
    }
    
    public float productPriceByIndex(int index) {
        String productpricewhole = MessageFormat.format(stringProductPriceWhole, "[" + index + "]");
        String productpricefraction = MessageFormat.format(stringProductPriceFraction, "[" + index + "]");
      
        Float ProductPrice = Float.parseFloat(getText(By.xpath(productpricewhole)).replaceAll(",", "") + "." + getText(By.xpath(productpricefraction)));
        
        return ProductPrice;
    }
    
    public ArrayList<ProductDataObject> sortProductList() {
       	ArrayList<ProductDataObject> ProductDataList = getProductList();
    	Collections.sort(ProductDataList, new SortByPrice());
		return ProductDataList;
    }
    
    public String convertObjectToJson(ArrayList<ProductDataObject> object) {
    	Gson gson = new Gson();
    	String Json = gson.toJson(object);
		return Json;
    }
    
    public void changeTab(String url) {
    	openNewTab();
    	goTo(url);
    }
}
