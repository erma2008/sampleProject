package StepDefinitions.JDBCStepDefs;

import Pages.JDBCPage.JDBCProjectPage;
import Utils.DBUtils;
import Utils.Driver;
import Utils.ExcelUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JDBCStepDefs {
    JDBCProjectPage pageJDBCProject;
    WebDriver driver;
    List<String> employeeInfoUI;
    List<Map<String,Object>> databaseData;
    @Given("User gets data first name and last name who work in Europe from Database")
    public void user_gets_data_first_name_and_last_name_who_work_in_Europe_from_Database() throws SQLException {
        DBUtils.establishConnection();
        driver = Driver.getDriver();
        driver.get("file:///Users/familymac/Downloads/HR%20Application.html");
        String query = "SELECT e.first_name, e.last_name\n" +
                "FROM employees e JOIN departments d ON\n" +
                "e.department_id=d.department_id\n" +
                "JOIN locations l ON\n" +
                "d.location_id=l.location_id\n" +
                "JOIN countries c ON\n" +
                "l.country_id=c.country_id\n" +
                "JOIN regions reg ON\n" +
                "c.region_id=reg.region_id\n" +
                "where reg.region_name='Europe'";
        databaseData = DBUtils.runQuery(query);
        pageJDBCProject = new JDBCProjectPage();
        employeeInfoUI = new ArrayList<>();
        for(int i=1; i< pageJDBCProject.europeEmployees.size();i++){
            String employeeInfo = pageJDBCProject.getEmployeeInfo(i,1,driver).getText()+" "
                    +pageJDBCProject.getEmployeeInfo(i,2,driver).getText();
            System.out.println(employeeInfo);
            employeeInfoUI.add(employeeInfo);
        }
    }
    @And("User validates their UI information")
    public void user_validates_their_UI_information() {
        List<String> employeeInfoDB = new ArrayList<>();
        for(int i=0;i<databaseData.size();i++){
            employeeInfoDB.add(databaseData.get(i).get("FIRST_NAME").toString()+" " +
                    databaseData.get(i).get("LAST_NAME").toString());
        }
        Assert.assertTrue(employeeInfoDB.containsAll(employeeInfoUI));
    }
    @And("User updates Actual # of Employees with number of employees from UI in Excel")
    public void user_updates_Actual_of_Employees_with_number_of_employees_from_UI_in_Excel() throws IOException {
        String fileName = "Project1";
        ExcelUtils.openExcelFile(fileName,"Sheet1");
        System.out.println("!!!!!!" + ExcelUtils.getCellValue(1,1));
        ExcelUtils.setCellValue(fileName,"Blahblah",1,1);
        System.out.println(ExcelUtils.getCellValue(1,1));
        String expectedSize = ExcelUtils.getCellValue(2,2);
        ExcelUtils.closeWorkbook();
        /*boolean passed = expectedSize == employeeInfoUI.size();
        if(passed){
            ExcelUtils.setCellValue(2,4,"Passed");
        }else {
            ExcelUtils.setCellValue(2,4,"Failed");
        }
        Assert.assertEquals(expectedSize,employeeInfoUI.size());
        Assert.assertTrue(passed);
         */
    }
    @Given("user gets Department Name and number of ‘Active’ employees and validate with their UI numbers")
    public void user_gets_Department_Name_and_number_of_Active_employees_and_validate_with_their_UI_numbers() {
    }
    @Then("update column Actual # of Employees in Excel with number of Active employees from UI")
    public void update_column_Actual_of_Employees_in_Excel_with_number_of_Active_employees_from_UI() {
    }
    @And("verify number with ‘expected # of Employees’ and update Test Execution Status")
    public void verify_number_with_expected_of_Employees_and_update_Test_Execution_Status() {
    }

}
