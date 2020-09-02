package API.jiraapi;

import API.utils.PayLoadUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static API.jiraapi.JiraIntro2.JSON;
import static API.utils.Constants.*;
import static io.restassured.RestAssured.given;

public class JiraIntro {



    @Test
    public void createStory(){
        //http://localhost:8080/rest/api/2/issue

        RestAssured.baseURI="http://localhost:8080";
        RestAssured.basePath="rest/api/2/issue";

        String storyBody= PayLoadUtil.getJiraIssuePayload(STORY,"dddefoul",
                "This is a story from JAVA",
                "API call from InteliJ",LOWEST_PRIORITY);

       Response response= given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .cookie("JSESSIONID",PayLoadUtil.generateCookie())
                .body(storyBody)
                .when().post().then().statusCode(201).extract().response();

    }



    @Test
    public void createBug(){
        //http://localhost:8080/rest/api/2/issue

        RestAssured.baseURI="http://localhost:8080";
        RestAssured.basePath="rest/api/2/issue";

        String storyBody= PayLoadUtil.getJiraIssuePayload(BUG,"dddefoul",
                "This is a bug from InteliJ",
                "API call from InteliJ",HIGH_PRIORITY);

        Response response= given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .cookie("JSESSIONID","EE23A285A592B18C9242685913AA0F1F")
                .body(storyBody)
                .when().post().then().statusCode(201).extract().response();

    }

    @Test
    public void createBugBaseAuth() throws URISyntaxException, IOException {
        //http://localhost:8080/rest/api/2/issue


        HttpClient httpClient= HttpClientBuilder.create().build();

        URIBuilder uriBuilder=new URIBuilder();

        uriBuilder.setScheme("http")
                .setHost("localhost:8080")
                .setPath("/rest/api/2/issue");
        HttpPost httpPost=new HttpPost(uriBuilder.build());
        httpPost.setHeader("Authorization","Basic ZGRkZWZvdWw6MWVybWVrczEyDQo=");
        httpPost.setHeader("Accept",JSON);
        httpPost.setHeader("Content-type",JSON);


        HttpEntity entity=new StringEntity(PayLoadUtil.getJiraIssuePayload(STORY,"dddefoul",
                "This is a bug from InteliJ",
                "API call from InteliJ",HIGH_PRIORITY));

        httpPost.setEntity(entity);

        HttpResponse response = httpClient.execute(httpPost);

        Assert.assertEquals(HttpStatus.SC_CREATED,response.getStatusLine().getStatusCode());




    }
}
