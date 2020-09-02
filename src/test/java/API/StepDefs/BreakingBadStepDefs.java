package API.StepDefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BreakingBadStepDefs {
    private static Response response;

    @When("the user send GET request to BB API")
    public void the_user_send_GET_request_to_BB_API() {
       response=given().accept(ContentType.JSON)
                .when().log().parameters().get("https://www.breakingbadapi.com/api/quotes");

    }

    @Then("the status code is {int}")
    public void the_status_code_is(Integer expectedStatusCode) {
       response.then().assertThat().statusCode(expectedStatusCode);
    }

    @Then("the user sees some quotes")
    public void the_user_sees_some_quotes() {
        List<Map<String,Object>> quotesList=response.getBody().as(new TypeRef<List<Map<String,Object>>>() {
        });
        Assert.assertEquals("Invalid Number of Quotes",70,quotesList.size());
        Assert.assertNotNull(quotesList);
        Matchers.hasSize(quotesList.size());

    }

}
