package API.StepDefs;

import API.utils.PayLoadUtil;
import Utils.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static API.utils.Constants.*;
import static io.restassured.RestAssured.given;

public class APItoUIStepDefs {
    WebDriver driver= Driver.getDriver();
    Response response;
    private static String summary="Create as story from api from UI verification";
    private static String description="Learn Jira API";

    @When("the user creates the story using api")
    public void the_user_creates_the_story_using_api() {

            //http://localhost:8080/rest/api/2/issue

            RestAssured.baseURI="http://localhost:8080";
            RestAssured.basePath="rest/api/2/issue";

            String storyBody= PayLoadUtil.getJiraIssuePayload(STORY,"dddefoul",description
                    ,summary
                    ,LOWEST_PRIORITY);

            response= given().accept(ContentType.JSON).contentType(ContentType.JSON)
                    .cookie(SESSIONID, PayLoadUtil.generateCookie())
                    .body(storyBody)
                    .when().post();



    }

    @Then("the status code {int}")
    public void the_status_code(Integer expectedStatusCode) {
        response.then().assertThat().statusCode(expectedStatusCode);
    }

    @Then("verify story is created on the JiraBoard")
    public void verify_story_is_created_on_the_JiraBoard() {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost:8080");


        Cookie jsessionIdCookie=new Cookie(SESSIONID, PayLoadUtil.generateCookie());
        driver.manage().addCookie(jsessionIdCookie);
        driver.get("http://localhost:8080");

        String issueKey=response.path("key");
       WebElement jiraBoard=driver.findElement(By.id("greenhopper_menu"));
       jiraBoard.click();
       WebElement tec_board=driver.findElement(By.id("rapidb_lnk_98_lnk"));
       tec_board.click();
       WebElement backlog=driver.findElement(By.xpath("//a[@href='/secure/RapidBoard.jspa?projectKey=TEC&rapidView=98&view=planning']"));
       backlog.click();



        WebElement issue=driver.findElement(By.xpath("//a[.='"+issueKey+"']"));

        Assert.assertTrue("UI verification of issue failed",issue.getText().contains(issueKey));


        WebElement issueSummary=driver.findElement(By.xpath("//div[@data-issue-key='"+issueKey+"']//div[@class='ghx-summary']/span"));
          //  Assert.assertEquals("Summary assertion fail",);
    }



}
