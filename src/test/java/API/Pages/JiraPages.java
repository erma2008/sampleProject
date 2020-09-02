package API.Pages;

import Utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JiraPages {
    public JiraPages(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id = "login-form-username")
   public WebElement userName;

    @FindBy(id = "login-form-password")
    public WebElement password;

    @FindBy(id = "login")
    public WebElement singInButton;

    @FindBy(id = "greenhopper_menu")
    public WebElement board;

    @FindBy(xpath = "//span[@jsselect='heading']")
    public WebElement errorMessage;
}
