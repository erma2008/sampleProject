package API.utils;

import Utils.ConfigReader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import static API.jiraapi.JiraIntro2.JSON;
import static io.restassured.RestAssured.given;

public class PayLoadUtil {

    public static String getPetPayload(int id){
        String requestBody = "{\n" +
                "  \"id\": "+id+",\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"Tarzan\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";
        return requestBody;
    }



    public static String getUserPayLoad(String name,String job){
        return "{\n" +
                "                \"name\": \""+name+"\",\n" +
                "                \"job\": \""+job+"\"\n" +
                "}";
    }

    public static String getAuthorizationPayload(){
        return "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}";
    }


    public static String erma(int id){
        return "{\n" +
                "  \"id\": "+id+",\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"doggie\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";
    }


public static String getJiraIssuePayload(String type, String assignee, String description, String summary, String priority){
        return "{\n" +
                "    \"fields\": {\n" +
                "        \"project\": {\n" +
                "            \"key\": \"TEC\"\n" +
                "        },\n" +
                "        \"assignee\": {\n" +
                "            \"name\": \""+assignee+"\"\n" +
                "        },\n" +
                "        \"summary\": \""+summary+"\",\n" +
                "        \"description\": \""+description+"\",\n" +
                "        \"issuetype\": {\n" +
                "            \"name\": \""+type+"\"\n" +
                "        },\n" +
                "        \"priority\": {\n" +
                "            \"name\": \""+priority+"\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
}

public static String getJiraSprintPayload(String name, int originBoard,String goal){

        return "{\n" +
                "  \"name\": \""+name+"\",\n" +
                "  \"startDate\": \"2020-04-11T15:22:00.000+10:00\",\n" +
                "  \"endDate\": \"2020-04-25T15:22:00.000+10:00\",\n" +
                "  \"originBoardId\": "+originBoard+",\n" +
                "  \"goal\": \""+goal+"\"\n" +
                "}";
}

public static String logInBody(){
        return "{\n" +
                "    \"username\": \""+ ConfigReader.getProperty("jiraUserName")+"\",\n" +
                "    \"password\": \""+ ConfigReader.getProperty("jiraPassword")+"\"\n" +
                "}";
}


    public static String generateCookie(){
        //http://localhost:8080/rest/auth/1/session
        RestAssured.baseURI="http://localhost:8080";
        RestAssured.basePath="rest/auth/1/session";
        Response response= given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(PayLoadUtil.logInBody())
                .when().post().then().statusCode(200).extract().response();

        Map<String,Object> responseBody=response.getBody().as(new TypeRef< Map<String,Object>>() { });

        Map<String,String> session= (Map<String,String>)responseBody.get("session");
//       String value=session.get("name")+"="+session.get("value");
//            System.out.println(value);
        return session.get("value");
    }


 @Test
    public static String generateCookieApache() throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http").setHost("localhost:8080").setPath("rest/auth/1/session");
        HttpPost httpPost = new HttpPost(uriBuilder.build());
        httpPost.addHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type",JSON);
        HttpEntity entity = new StringEntity(logInBody());
        httpPost.setEntity(entity);
        HttpResponse response = httpClient.execute(httpPost);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> data=objectMapper.readValue(response.getEntity().getContent(),new TypeReference<Map<String,Object>>(){});
        Map<String,String> session=( Map<String,String>)data.get("session");


        return session.get("value");
    }

    public static String addJiraCommentPayload(String comment){
        return "{\n" +
                "\t\"body\":\""+comment+"\"\n" +
                "}";
    }

    }
