package Pages.IceHrm;

import Utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IceHrmHomePage {

    public IceHrmHomePage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//span[.='Admin']")
    public WebElement adminButton;

    @FindBy(xpath = "//ul[@id='admin_Admin']//li")
    public List<WebElement> adminHeaders;

    @FindBy(xpath = "//span[.='Personal Information']")
    public WebElement personalInformation;

    @FindBy(xpath = "//ul[@id='module_Personal_Information']//li")
    public List<WebElement> personalInfoList;

    @FindBy(xpath = "//i[@class='fa fa-check-square']")
    public WebElement qualificationButton;

    @FindBy(id = "tabEducation")
    public WebElement educationButton;

    @FindBy(id = "tabLanguage")
    public WebElement languageButton;

    @FindBy(xpath = "//div[@id='tabPageEducation']//td[1]")
    public List<WebElement> personalInfoNames;

    @FindBy(xpath = "//div[@id='tabPageEducation']//td[2]")
    public List<WebElement> personalInfoValues;

    @FindBy(xpath = "//div[@id='Language']//td[1]")
    public List<WebElement> shortNames;

    @FindBy(xpath = "//div[@id='Language']//td[2]")
    public List<WebElement> longNames;

    @FindBy(xpath = "//ul[@id='admin_Admin']//a//i[@class='fa fa-list-alt']")
    public WebElement projectButton;

    @FindBy(xpath = "//tbody//td[1]")
    public List<WebElement> names;

    @FindBy(xpath = "//tbody//td[3]")
    public List<WebElement> addresses;

    @FindBy(xpath = "//tbody//td[4]")
    public List<WebElement> contactNumbers;



    /*
    Create one method will take two parameter as a list of keys and values
    This method will return map<k,v>
     */

    public Map<String, String> getQualifiactionInfo(List<WebElement> keys, List<WebElement> values){
        Map<String, String> allInfo=new HashMap<>();

        for(int i=0;i<keys.size();i++){

            allInfo.put(keys.get(i).getText(), values.get(i).getText());
        }

        return allInfo;
    }












}
