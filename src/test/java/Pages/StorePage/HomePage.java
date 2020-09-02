package Pages.StorePage;

import Utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public HomePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id="search_query_top")
    public WebElement searchBox;

    @FindBy(xpath = "//button[@name='submit_search']")
    public WebElement searchButton;

    @FindBy(xpath = "//h1/span[1]")
    public WebElement searchResult;

}
