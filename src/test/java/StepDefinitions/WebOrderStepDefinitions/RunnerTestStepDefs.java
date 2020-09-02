package StepDefinitions.WebOrderStepDefinitions;

import io.cucumber.java.en.Given;

public class RunnerTestStepDefs {

    @Given("The user send the key")
    public void the_user_send_the_key() {
        System.out.println("This is for test 1");
    }

    @Given("The user click the button")
    public void the_user_click_the_button() {

        System.out.println("This is for test 2");

    }
}
