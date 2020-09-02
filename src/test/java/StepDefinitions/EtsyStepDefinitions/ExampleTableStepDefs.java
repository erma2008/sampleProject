package StepDefinitions.EtsyStepDefinitions;

import Pages.EtsyPage.EtsyHomePage;
import Utils.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class ExampleTableStepDefs {

    EtsyHomePage etsyHomePage=new EtsyHomePage();
    WebDriver driver= Driver.getDriver();
    @When("the user search with {string}")
    public void the_user_search_with(String searchValue) {

        etsyHomePage.searchBox.sendKeys(searchValue);
        etsyHomePage.searchButton.click();

    }

    @Then("the user validate the {string}")
    public void the_user_validate_the(String expectedTitle) {

        String actualTitle=driver.getTitle();
        Assert.assertTrue(actualTitle.equals(expectedTitle));
    }
}
