package Pages.IceHrm;

import Utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class IceHrmPage {

    public IceHrmPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//input[@id='username']")
    public WebElement userName;
    @FindBy(xpath = "//input[@id='password']")
    public WebElement password;
    @FindBy(xpath = "//button[@onclick='submitLogin();return false;']")
    public WebElement loginButton;
    @FindBy(xpath = "//i[@class='fa fa-file-archive']")
    public WebElement payrollButton;
    @FindBy(xpath = "//i[@class='fa fa-money-check-alt']")
    public WebElement salaryButton;
    @FindBy(xpath = "//button[.='Add New ']")
    public WebElement addNewButton;
    @FindBy(id="code")
    public WebElement codeBox;
    @FindBy(id="name")
    public WebElement nameBox;
    @FindBy(xpath = "//button[.=' Save']")
    public WebElement saveButton;
    @FindBy(xpath = "//ul[@class='pagination']//a")
    public List<WebElement> pagination;
    @FindBy(xpath = "//table[@id='grid']//tbody//tr[last()]//td[1]")
    public WebElement lastCode;
    @FindBy(xpath ="//table[@id='grid']//tbody//tr[last()]//td[2]" )
    public WebElement lastName;
    @FindBy(xpath = "//ul[@class='pagination']//li[last()]")
    public WebElement nextButton;


    public void findLastPage() throws InterruptedException {
        int count=2;
        while(nextButton.getAttribute("class").equals("next")){
            clickPagination(count);
            count++;
        }

    }

    public void clickLastPage(){
       pagination.get(pagination.size()-1).click();
    }
    // this method will get number of the page and it will click that page

    public void clickPagination(int number) throws InterruptedException {
        String pageNumber=""+number;
        Thread.sleep(500);
        for(WebElement element : pagination){
            if(element.getText().equals(pageNumber)){
                element.click();
                break;
            }
        }
    }


}
