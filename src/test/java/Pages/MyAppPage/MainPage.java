package Pages.MyAppPage;

import Utils.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage {
    WebDriver driver;
    public MainPage(){
        driver= Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//table//tr[1]//td")
    public List<WebElement> tableFirstRow;
    @FindBy(xpath = "//table//tr[2]//td")
    public List<WebElement> tableSecondRow;
    @FindBy(xpath = "//table//tr[3]//td")
    public List<WebElement> tableThirdRow;
    @FindBy(xpath = "//table//tr[4]//td")
    public List<WebElement> tableFourthRow;

}
