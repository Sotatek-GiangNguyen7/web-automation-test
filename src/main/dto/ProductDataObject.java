package main.dto;

public class ProductDataObject {
	
	public String websiteName;
	
	public String productName;
	
	public float productPrice;
	
	public String productLink;

    public ProductDataObject(String websiteName, String productName, float productPrice, String productLink) {
        super();
        this.websiteName = websiteName;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productLink = productLink;
    }
	public ProductDataObject() {
	}
}

