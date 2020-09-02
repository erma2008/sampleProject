package StepDefinitions.WebOrderStepDefinitions;

import Pages.WebOrderPage.AllProductsPage;
import Pages.WebOrderPage.HomePage;
import Pages.WebOrderPage.OrderPage;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;

public class AllProductsStepDefs {

    HomePage homePage=new HomePage();
    AllProductsPage allProductsPage=new AllProductsPage();
    OrderPage orderPage=new OrderPage();

    @Then("the user click view all products button")
    public void the_user_click_view_all_products_button() {

        homePage.viewAllProductsButton.click();

    }

    @Then("the user validate product table")
    public void the_user_validate_product_table() {

       List<String> actualList=allProductsPage.getProductList();
       List<String> expectedList=allProductsPage.expectedData();

       Assert.assertTrue(actualList.containsAll(expectedList));
    }


}
