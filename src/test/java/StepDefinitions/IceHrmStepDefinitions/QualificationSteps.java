package StepDefinitions.IceHrmStepDefinitions;

import Pages.IceHrm.IceHrmHomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class QualificationSteps {

    IceHrmHomePage iceHrmHomePage=new IceHrmHomePage();

    @Given("the user click qualification setup button")
    public void the_user_click_qualification_setup_button() {

        iceHrmHomePage.qualificationButton.click();
    }

    @Then("the user click education tab")
    public void the_user_click_education_tab() {

        iceHrmHomePage.educationButton.click();

    }

    @Then("the user validate personal info displayed")
    public void the_user_validate_personal_info_displayed(io.cucumber.datatable.DataTable dataTable) {

       Map<String, String> expectedPersonInfo=dataTable.asMap(String.class, String.class);
       Map<String, String> actualPersonInfo=iceHrmHomePage.getQualifiactionInfo(iceHrmHomePage.personalInfoNames, iceHrmHomePage.personalInfoValues);
       Set<String> keys=expectedPersonInfo.keySet();

       for(String key: keys){

           Assert.assertEquals(expectedPersonInfo.get(key), actualPersonInfo.get(key));
       }

    }


    @Then("the user click language button")
    public void the_user_click_language_button() {
       iceHrmHomePage.languageButton.click();
    }

    @Then("the user validate languages with short names")
    public void the_user_validate_languages_with_short_names(Map<String,String> expectedNames) {

        Map<String,String> actualNames=iceHrmHomePage.getQualifiactionInfo(iceHrmHomePage.shortNames, iceHrmHomePage.longNames);
        Set<String> keys=actualNames.keySet();

        for(String key: keys){

            Assert.assertEquals(expectedNames.get(key),actualNames.get(key));
        }

    }



    @Then("the user validate languages with short names with header")
    public void the_user_validate_languages_with_short_names_with_header(List<Map<String,String>> listOfLanguages) {

        for(Map<String,String> map: listOfLanguages){
            System.out.println(map);
            if(map.get("Name").equals("tt")){

                System.out.println(map.get("Description"));
                System.out.println(map.get("Name"));

            }
        }

    }


    @Given("the user click project client setup button")
    public void the_user_click_project_client_setup_button() {

        iceHrmHomePage.projectButton.click();
    }

    @Then("the user validate client info")
    public void the_user_validate_client_info(List<Map<String,String>> clientInfo) {

        System.out.println(clientInfo);
        for(int i=0;i<clientInfo.size();i++){
            String expectedName=clientInfo.get(i).get("Name");
            String expectedAddress=clientInfo.get(i).get("Address");
            String expectedNumber=clientInfo.get(i).get("Contact Number");
            String actualName=iceHrmHomePage.names.get(i).getText();
            String actualAddress=iceHrmHomePage.addresses.get(i).getText();
            String actualNumber=iceHrmHomePage.contactNumbers.get(i).getText();
            Assert.assertEquals(expectedName,actualName);
            Assert.assertEquals(expectedAddress,actualAddress);
            Assert.assertEquals(expectedNumber,actualNumber);

        }


    }



}
