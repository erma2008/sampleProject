package Pages.WebOrderPage;

import Utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class AllProductsPage {

    public AllProductsPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//table[@class='ProductsTable']//tr")
    public List<WebElement> productList;

    // this will be your actual data
    public List<String> getProductList(){

        List<String> productData=new ArrayList<>();
        for(WebElement element : productList){

            productData.add(element.getText());
        }

        return productData;
    }

    public List<String> expectedData(){
        List<String> expectedList=new ArrayList<>();

        expectedList.add("Product name Price Discount");
        expectedList.add("MyMoney $100 8%");
        expectedList.add("FamilyAlbum $80 15%");
        expectedList.add("ScreenSaver $20 10%");

        return expectedList;
    }

}
