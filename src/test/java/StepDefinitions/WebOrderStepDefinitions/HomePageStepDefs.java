package StepDefinitions.WebOrderStepDefinitions;

import Pages.WebOrderPage.HomePage;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomePageStepDefs {

    HomePage page=new HomePage();

    @Then("the user validate order menu list")
    public void the_user_validate_order_menu_list() {
        // this is for api response or database
        List<String> expected=page.orderMenuData();
        // this is coming from the website
        List<String> actual=new ArrayList<>();

        // used this first loop to get the text from web elements
        for(WebElement element:page.orderMenu){

            actual.add(element.getText());
        }

        // used this loop for assertion
        for(int i=0;i<actual.size();i++){

            Assert.assertEquals(expected.get(i),actual.get(i));
        }

        System.out.println("This is for menu");

    }

}
