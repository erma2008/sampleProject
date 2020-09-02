package StepDefinitions.MyAppStepDefinitions;

import Pages.MyAppPage.MainPage;
import Utils.ConfigReader;
import Utils.DBUtils;
import Utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MyAppSteps {

    WebDriver driver= Driver.getDriver();
    List<Map<String,Object>> listOfUIdata=new ArrayList<>();
    MainPage mainPage=new MainPage();

    @Given("User navigates to MyApplication page")
    public void user_navigates_to_MyApplication_page() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(ConfigReader.getProperty("MyApp"));

    }

    @Then("User gets data from UI table")
    public void user_gets_data_from_UI_table() {
        Map<String,Object> uiRow1=new HashMap<>();
        uiRow1.put("FIRST_NAME",mainPage.tableFirstRow.get(0).getText());
        uiRow1.put("LAST_NAME",mainPage.tableFirstRow.get(1).getText());
        uiRow1.put("EMPLOYEE_ID",mainPage.tableFirstRow.get(2).getText());
        listOfUIdata.add(uiRow1);

        Map<String,Object> uiRow2=new HashMap<>();
        uiRow2.put("FIRST_NAME",mainPage.tableSecondRow.get(0).getText());
        uiRow2.put("LAST_NAME",mainPage.tableSecondRow.get(1).getText());
        uiRow2.put("EMPLOYEE_ID",mainPage.tableSecondRow.get(2).getText());
        listOfUIdata.add(uiRow2);

        Map<String,Object> uiRow3=new HashMap<>();
        uiRow3.put("FIRST_NAME",mainPage.tableThirdRow.get(0).getText());
        uiRow3.put("LAST_NAME",mainPage.tableThirdRow.get(1).getText());
        uiRow3.put("EMPLOYEE_ID",mainPage.tableThirdRow.get(2).getText());
        listOfUIdata.add(uiRow3);

        Map<String,Object> uiRow4=new HashMap<>();
        uiRow4.put("FIRST_NAME",mainPage.tableFourthRow.get(0).getText());
        uiRow4.put("LAST_NAME",mainPage.tableFourthRow.get(1).getText());
        uiRow4.put("EMPLOYEE_ID",mainPage.tableFourthRow.get(2).getText());
        listOfUIdata.add(uiRow4);


    }

    @Then("User verify data is matched with DataBase data")
    public void user_verify_data_is_matched_with_DataBase_data() throws SQLException {
        DBUtils.establishConnection();
        List<Map<String, Object>> listOfDBData = DBUtils.runQuery("select employee_id, last_name, first_name from employees");
        DBUtils.closeConnection();
        // listOfUIData -> stores all data from UI
        // listOfDBData -> stores all data from DB
        System.out.println(listOfUIdata);
//        Assert.assertTrue(listOfDBData.containsAll(listOfUIData));
        int count =0;
        for(int i =0; i<listOfUIdata.size(); i++){
            for(int a=0; a<listOfDBData.size(); a++){
                if(listOfUIdata.get(i).get("EMPLOYEE_ID").toString().equals(listOfDBData.get(a).get("EMPLOYEE_ID").toString()) ){
                    Assert.assertEquals(listOfUIdata.get(i).get("FIRST_NAME").toString(),listOfDBData.get(a).get("FIRST_NAME").toString() );
                    Assert.assertEquals(listOfUIdata.get(i).get("LAST_NAME").toString(),listOfDBData.get(a).get("LAST_NAME").toString() );
                    count++;
                }
            }
        }
        Assert.assertEquals(listOfUIdata.size(),count);
    }

    }


