package Pages.JDBCPage;


import Utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class JDBCProjectPage {
    public JDBCProjectPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(xpath = "//th[.='First Name']/../..//following-sibling::tbody//tr")
    public List<WebElement> europeEmployees;
    public WebElement getEmployeeInfo(int row, int column, WebDriver driver){
        String xpath = "//th[.='First Name']/../..//following-sibling::tbody//tr["+row+"]//td["+column+"]";
        WebElement element = driver.findElement(By.xpath(xpath));
        return element;
    }
}

