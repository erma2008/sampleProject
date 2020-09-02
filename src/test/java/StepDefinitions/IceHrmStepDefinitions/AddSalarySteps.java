package StepDefinitions.IceHrmStepDefinitions;

import Pages.IceHrm.IceHrmPage;
import Utils.ConfigReader;
import Utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class AddSalarySteps {

    WebDriver driver= Driver.getDriver();
    IceHrmPage iceHrmPage=new IceHrmPage();

    @Given("the user navigate to icehrm website")
    public void the_user_navigate_to_icehrm_website() {
        driver.get(ConfigReader.getProperty("iceHrmUrl"));
        // to be able get the cookies from website
        //driver.manage().getCookies();
        //driver.manage().getCookieNamed("nameOfCookies");
    }

    @When("the user provide icehrm username {string}")
    public void the_user_provide_icehrm_username(String username) {

        iceHrmPage.userName.sendKeys(username);

    }

    @When("the user provide icehrm password {string}")
    public void the_user_provide_icehrm_password(String password) {
       iceHrmPage.password.sendKeys(password);
    }

    @Then("the user click sign in button")
    public void the_user_click_sign_in_button() {

        iceHrmPage.loginButton.click();

    }

    @Given("the user click the payroll button")
    public void the_user_click_the_payroll_button() {
        iceHrmPage.payrollButton.click();
    }

    @Given("the user click the salary button")
    public void the_user_click_the_salary_button() {
        iceHrmPage.salaryButton.click();
    }

    @Given("the user click the add new button")
    public void the_user_click_the_add_new_button() {

        iceHrmPage.addNewButton.click();
    }

    @Given("the user enter the name {string}")
    public void the_user_enter_the_name(String name) {
       iceHrmPage.nameBox.sendKeys(name);
    }

    @Given("the user enter the code {string}")
    public void the_user_enter_the_code(String code) {

        iceHrmPage.codeBox.sendKeys(code);
    }


    @Given("the user click the save button")
    public void the_user_click_the_save_button() {

        iceHrmPage.saveButton.click();

    }

    @Then("the user validate {string} and {string} are saved")
    public void the_user_validate_and_are_saved(String expectedCode, String expectedName) throws InterruptedException {

       // Thread.sleep(1000);
       iceHrmPage.findLastPage();
        String actualCode=iceHrmPage.lastCode.getText();
        String actualName=iceHrmPage.lastName.getText();

        Assert.assertTrue(actualCode.equals(expectedCode));
        Assert.assertTrue(actualName.equals(expectedName));
    }

}
