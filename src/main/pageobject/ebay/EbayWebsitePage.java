package main.pageobject.ebay;

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

public class EbayWebsitePage extends WebDriverAction {
	private By searchBox = By.xpath("//input[@id=\"gh-ac\"]");
	private By searchButton = By.xpath("//input[@id=\"gh-btn\"]");

	private By menu = By.xpath("//span//span[@class=\"fake-menu-button\"]");
	private By customize = By.xpath("//button[@aria-label=\"Customize\"]");
	private By convertPrice = By.xpath("//input[@id=\"cust_fcpd-1\"]");
	private By applyChange = By.xpath("//button[text()='Apply changes']");
	
	String stringProductLink = "//ul[@class=\"srp-results srp-list clearfix\"]//li[@data-view=\"mi:1686|iid:{0}\"]//a[@class=\"s-item__link\"]";
	String stringProductName = "//ul[@class=\"srp-results srp-list clearfix\"]//li[@class=\"s-item s-item__pl-on-bottom\"]{0}//h3[@class=\"s-item__title\"]";
	String stringProductPrice = "//ul[@class=\"srp-results srp-list clearfix\"]//li[@data-view=\"mi:1686|iid:{0}\"]//span[@class=\"s-item__price\"]";
	public EbayWebsitePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public void searchProduct(String key) {
		sendKey(searchBox, key);
	}
	public void clickButtonSearch() {
		clickElement(searchButton);
	}
	public void convertPriceToUsd() {
		clickElement(menu);
		clickElement(customize);
		wait(3000);
		clickElement(convertPrice);
		clickElement(applyChange);
		
	}
    public ArrayList<ProductDataObject> getProductList() {
    	ArrayList<ProductDataObject> list1 = new ArrayList<ProductDataObject>();
        for (int x = 1; x <= nameList().size(); x++){
        	ProductDataObject productDataObject = new ProductDataObject();
        	productDataObject.websiteName = getWebsiteName();
			productDataObject.productName = productNameByIndex(x);
			productDataObject.productPrice = productPriceByIndex(x);
			productDataObject.productLink = productLinkByIndex(x);
			list1.add(productDataObject);
        }
		return list1;
    	
    }
    public List<String> nameList()
    {
        String productname = MessageFormat.format(stringProductName, "");
        return elementsToListText(By.xpath(productname));
    }
    public String productLinkByIndex(int index) {
        String productlink = MessageFormat.format(stringProductLink, index);
        return getLink(By.xpath(productlink));
    }
    public String productNameByIndex(int index) {
        String productname = MessageFormat.format(stringProductName,"[" + index + "]");
        return getText(By.xpath(productname));
    }
    public float productPriceByIndex(int index) {
        String productprice = MessageFormat.format(stringProductPrice, index);
        float ProductPrice1 = Float.parseFloat(getText(By.xpath(productprice)).substring(1).replaceAll("to.*", "").replaceAll(",", ""));
        
        return ProductPrice1;
    }
    public ArrayList<ProductDataObject> sortProductList(ArrayList<ProductDataObject> object) {
    	Collections.sort(object, new SortByPrice());
		return object;
    }
    public void showListProduct(ArrayList<ProductDataObject> ProductAmazonList) {
		for (ProductDataObject product:ProductAmazonList) {
			System.out.printf("|%-50s | %-100s | %-20s | %s %n","WebsiteName: " + product.websiteName , "ProductName: " + product.productName , "ProductPrice: " + product.productPrice , "LinkProduct: " + product.productLink);
		}
    }
    public void mergeListProduct(ArrayList<ProductDataObject> ProductAmazonList, ArrayList<ProductDataObject> ProductEbayList) {
    	ProductAmazonList.addAll(ProductEbayList);
    }
    public String convertObjectToJson(ArrayList<ProductDataObject> object) {
    	Gson gson = new Gson();
    	String Json = gson.toJson(object);
		return Json;
    }
}
