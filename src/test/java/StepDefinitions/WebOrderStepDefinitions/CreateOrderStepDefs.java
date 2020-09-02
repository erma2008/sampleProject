package StepDefinitions.WebOrderStepDefinitions;

import Pages.WebOrderPage.AllOrdersPage;
import Pages.WebOrderPage.HomePage;
import Pages.WebOrderPage.OrderPage;
import Utils.BrowserUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CreateOrderStepDefs {

    OrderPage orderPage=new OrderPage();
    HomePage homePage=new HomePage();
    AllOrdersPage allOrdersPage=new AllOrdersPage();

    @Given("The user click order button")
    public void the_user_click_order_button() {

        orderPage.orderButton.click();

    }

    @Given("The user select product {string}")
    public void the_user_select_product(String productName) {

        BrowserUtils.selectByVisibleText(orderPage.product,productName);

    }

    @Given("The user give the quantity {string}")
    public void the_user_give_the_quantity(String quantity) {

        orderPage.quantity.sendKeys(quantity);
    }

    @Then("The user choose the card {string}")
    public void the_user_choose_the_card(String cardName) {
        orderPage.selectCard(cardName);
    }

    @Then("The user provide cardNumber {string}")
    public void the_user_provide_cardNumber(String cardNumber) {
        orderPage.cardNumber.sendKeys(cardNumber);
    }

    @Then("The user enter expiration date {string}")
    public void the_user_enter_expiration_date(String expirationDate) {
        orderPage.expirationDate.sendKeys(expirationDate);
    }



    @Then("The user click process button")
    public void the_user_click_process_button() {

        orderPage.processButton.click();
    }

    @Then("The user click view all orders button")
    public void the_user_click_view_all_orders_button() {

        homePage.viewAllOrdersButton.click();
    }

    @Then("The user validate new order info {string} {string} {string} {string} {string} {string} {string} {string} {string} {string}")
    public void the_user_validate_new_order_info(String product, String quantity, String customer, String street, String city, String state,
                                                 String zip, String card, String cardNumber, String expiration) {

        List<WebElement> productInfo=allOrdersPage.orderInfo;
        Assert.assertTrue(productInfo.get(1).getText().equals(customer));
        Assert.assertTrue(productInfo.get(2).getText().equals(product));
        Assert.assertTrue(productInfo.get(3).getText().equals(quantity));
        Assert.assertTrue(productInfo.get(5).getText().equals(street));
        Assert.assertTrue(productInfo.get(6).getText().equals(city));
        Assert.assertTrue(productInfo.get(7).getText().equals(state));
        Assert.assertTrue(productInfo.get(8).getText().equals(zip));
        Assert.assertTrue(productInfo.get(9).getText().equals(card));
        Assert.assertTrue(productInfo.get(10).getText().equals(cardNumber));
        Assert.assertTrue(productInfo.get(11).getText().equals(expiration));

    }

}
