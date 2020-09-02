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

import static API.utils.Constants.*;
import static io.restassured.RestAssured.given;

public class JiraIntro2 {

public static final String JSON="application/json";

    public static final String COOKIE="JSESSIONID="+ PayLoadUtil.generateCookie();

    @Test
    public void jiraPostRequest() throws URISyntaxException, IOException {
        HttpClient httpClient= HttpClientBuilder.create().build();

        //  //http://localhost:8080/rest/api/2/issue
        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("http")
                .setHost("localhost:8080")
                .setPath("rest/api/2/issue");

        HttpPost post=new HttpPost(uriBuilder.build());

        post.setHeader("Accept",JSON);
        post.setHeader("Content-Type",JSON);
        post.setHeader("Cookie",COOKIE);


        HttpEntity entity=new StringEntity(PayLoadUtil.getJiraIssuePayload("Story","dddefoul","Apache http api call 2",
                "Http client made this Api call 2",HIGHEST_PRIORITY));


        post.setEntity(entity);

        HttpResponse response = httpClient.execute(post);


        Assert.assertEquals(201,response.getStatusLine().getStatusCode());
    }


    @Test
    public void createSprint() throws URISyntaxException, IOException {
        // http://localhost:8080/rest/agile/1.0/sprint

        HttpClient httpClient=HttpClientBuilder.create().build();

        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("http")
                .setHost("localhost:8080")
                .setPath("/rest/agile/1.0/sprint");

        HttpPost httpPost=new HttpPost(uriBuilder.build());
        httpPost.setHeader("Accept",JSON);
        httpPost.setHeader("Content-Type",JSON);
        httpPost.setHeader("Cookie",COOKIE);





        HttpEntity entity=new StringEntity(PayLoadUtil.getJiraSprintPayload("Training Day try 2",65,"WE need to train a lot"));
        httpPost.setEntity(entity);

        HttpResponse response = httpClient.execute(httpPost);


        Assert.assertEquals(HttpStatus.SC_CREATED,response.getStatusLine().getStatusCode());


    }




    @Test
    public void createStory(){
        //http://localhost:8080/rest/api/2/issue

        RestAssured.baseURI="http://localhost:8080";
        RestAssured.basePath="rest/api/2/issue";

        String storyBody= PayLoadUtil.getJiraIssuePayload(STORY,"dddefoul",
                "This is a story from JAVA adn 00 by Baha",
                "API call from InteliJ by Baha",LOWEST_PRIORITY);

        System.out.println(PayLoadUtil.generateCookie());
        //85A5EB04B2078773ED23E713AC5011DD

        Response response= given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .cookie("JSESSIONID", PayLoadUtil.generateCookie())
                .body(storyBody)
                .when().post().then().statusCode(201).extract().response();

    }




}
