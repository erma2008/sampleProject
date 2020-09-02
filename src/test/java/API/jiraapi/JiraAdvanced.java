package API.jiraapi;

import API.pojo.CommentPojo;
import API.pojo.JiraBoardPojo;
import API.utils.PayLoadUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static API.utils.Constants.SESSIONID;
import static io.restassured.RestAssured.given;

public class JiraAdvanced {

    @Test
    public void verifyBoard(){
        //http://localhost:8080/rest/agile/1.0/board
        RestAssured.baseURI="http://localhost:8080";
        RestAssured.basePath="/rest/agile/1.0/board";

        Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .cookie(SESSIONID, PayLoadUtil.generateCookie())
                .when().request(Method.GET).then().statusCode(200).extract().response();

        JiraBoardPojo jiraBoardPojo=response.getBody().as(JiraBoardPojo.class);

        System.out.println(jiraBoardPojo.getValues().get(0).get("id"));


        String errorMessage="Board %s veification failure";
        Assert.assertEquals(String.format(errorMessage,"size"),jiraBoardPojo.getTotal(),jiraBoardPojo.getValues().size());
        Assert.assertEquals(String.format(errorMessage,"type"),jiraBoardPojo.getValues().get(0).get("type"),"scrum");
        Assert.assertTrue(String.format(errorMessage,"name"),jiraBoardPojo.getValues().get(0).get("name").toString().startsWith("SPR"));
        Assert.assertEquals(String.format(errorMessage,"id"),jiraBoardPojo.getValues().get(0).get("id"),1);

    }


    @Test
    public void addComment(){
        // http://localhost:8080/rest/api/2/issue/TEC-2/comment
        RestAssured.baseURI="http://localhost:8080";
        RestAssured.basePath="rest/api/2/issue";
        //TEC-2/comment
        String comment="Scrum team will prioritize this bug and fix it TODAY";
        Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .cookie(SESSIONID, PayLoadUtil.generateCookie())
                .body(PayLoadUtil.addJiraCommentPayload(comment))
                .when().post("{issueId}/comment","TEC-2").then().statusCode(201).extract().response();
        CommentPojo commentPojo= response.getBody().as(CommentPojo.class);

        Assert.assertEquals(comment,commentPojo.getBody());
    }


    @Test
    public void addUserStoriesToSprint(){
      //  http://localhost:8080/rest/agile/1.0/sprint/{sprintName}/issue
        RestAssured.baseURI="http://localhost:8080";
        RestAssured.basePath="/rest/agile/1.0/sprint/TEC Sprint 1/issue";

        String body="{\n" +
                "  \"issues\": [\n" +
                "    \"TEC-11\"\n" +
                "  ]\n" +
                "}";

        Response response=given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .cookie(SESSIONID,PayLoadUtil.generateCookie())
                .body(body)
                .when().post().then().statusCode(204).extract().response();



    }

}
