package Pages.WebOrderPage;

import Utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage {

    public OrderPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(xpath = "// ul[@id='ctl00_menu']//a[.='Order']")
    public WebElement orderButton;

    @FindBy(id = "ctl00_MainContent_fmwOrder_ddlProduct")
    public WebElement product;
    @FindBy(id = "ctl00_MainContent_fmwOrder_txtQuantity")
    public WebElement quantity;

    @FindBy(xpath = "//input[@name='ctl00$MainContent$fmwOrder$cardList']")
    public List<WebElement> cardName;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox6")
    public WebElement cardNumber;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox1")
    public WebElement expirationDate;

    @FindBy(id="ctl00_MainContent_fmwOrder_InsertButton")
    public WebElement processButton;

    public void selectCard(String cardName) {
        // if card name is equals to visa click first element,
        // equals to MasterCard click second element
        // equals to American Express click third element
        switch (cardName) {
            case "Visa":
                this.cardName.get(0).click();
                break;
            case "MasterCard":
                this.cardName.get(1).click();
                break;
            case "American Express":
                this.cardName.get(2).click();
                break;
        }

    }
}
