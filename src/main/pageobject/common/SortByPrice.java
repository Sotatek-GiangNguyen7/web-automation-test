package main.pageobject.common;

import main.dto.ProductDataObject;

import java.util.Comparator;



public class SortByPrice implements Comparator<ProductDataObject>
{
    // Used for sorting in ascending order 
    public int compare(ProductDataObject a, ProductDataObject b)
    {
        return  (int) (a.productPrice - b.productPrice);
    }
}
