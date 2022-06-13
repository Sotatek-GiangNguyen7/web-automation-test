package test;

import core.TestSetUp;
import main.config.Constant;
import main.dto.ProductDataObject;
import main.pageobject.amazon.AmazonWebsitePage;
import main.pageobject.ebay.EbayWebsitePage;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class ProductListTest extends TestSetUp {
    @Test
    public void searchProduct() throws InterruptedException {
    	AmazonWebsitePage amazonWebitePage = new AmazonWebsitePage(driver);
    	
    	//Search Iphone 11 on Amazon
    	amazonWebitePage.searchProduct(Constant.namePhone);
    	amazonWebitePage.clickButtonSearch();
    	
    	//Get List product from UI 
       	ArrayList<ProductDataObject> productAmazonList = amazonWebitePage.getProductList();
    	
    	//switch to Ebay
    	amazonWebitePage.changeTab(Constant.linkWeb2);
      	EbayWebsitePage ebayWebSitePage = new EbayWebsitePage(driver);
    	
    	//Search Iphone 11 on Ebay
    	ebayWebSitePage.searchProduct(Constant.namePhone);
    	ebayWebSitePage.clickButtonSearch();
    	ebayWebSitePage.convertPriceToUsd();
    	
    	//Get List product from UI
       	ArrayList<ProductDataObject> productEbayList = ebayWebSitePage.getProductList();
       	ebayWebSitePage.mergeListProduct(productAmazonList, productEbayList);
       	
       	//Sort product 
       	ebayWebSitePage.sortProductList(productAmazonList);
       	
    	//Show list product in ascending order of price
       	ebayWebSitePage.showListProduct(productAmazonList);
    }
}
