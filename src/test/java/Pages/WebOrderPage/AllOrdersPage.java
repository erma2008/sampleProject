package Pages.WebOrderPage;

import Utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AllOrdersPage {


    public AllOrdersPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//h2")
    public WebElement ordersHeader;

    @FindBy(xpath = "//td[.='123456789012']/following-sibling::td[2]/input")
    public WebElement editButton;

    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']//tbody//tr[2]//td")
    public List<WebElement> orderInfo;

}
