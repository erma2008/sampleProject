package StepDefinitions.IceHrmStepDefinitions;

import Pages.IceHrm.IceHrmHomePage;
import Utils.BrowserUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class AdminHeaders {

    IceHrmHomePage iceHrmHomePage=new IceHrmHomePage();

    @When("the user click the admin button")
    public void the_user_click_the_admin_button() {
        iceHrmHomePage.adminButton.click();
    }

    @Then("the user should see following headers")
    public void the_user_should_see_following_headers(io.cucumber.datatable.DataTable dataTable) {
        /*
        dataTable.asList();--> asList method will return list of String
         */
        List<String> expectedHeaders=dataTable.asList();
        List<String> actualHeaders= BrowserUtils.getTextList(iceHrmHomePage.adminHeaders);

        Assert.assertTrue(actualHeaders.containsAll(expectedHeaders));

    }



    @When("the user click the personal information")
    public void the_user_click_the_personal_information() {
        iceHrmHomePage.personalInformation.click();
    }

    @Then("the user should see following personal info headers")
    public void the_user_should_see_following_personal_info_headers(List<String> expectedHeaders) throws InterruptedException {
        Thread.sleep(1000);
        List<String> actualInfoHeaders=BrowserUtils.getTextList(iceHrmHomePage.personalInfoList);

      Assert.assertArrayEquals(expectedHeaders.toArray(), actualInfoHeaders.toArray());

    }


    @When("the user gives the personal information")
    public void the_user_gives_the_personal_information(Map<String, String > personInfo) {

        // if the value is equals to 2223334444, print the key
        Set<String> keys=personInfo.keySet();
        for(String key: keys){
            if(personInfo.get(key).equals("2223334444")){
                System.out.println(key);
            }
        }
    }


}
