package API.restassured;

import API.pojo.PetPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAssuredPost {

    @Test
    public void createUser(){
        //https://reqres.in/api/users >POST request

        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="api/users";

        //providing request body as map
        Map<String,String> reqBody=new HashMap<>();
        reqBody.put("name","techtorial");
        reqBody.put("job","SDET");

        Response response=given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(reqBody)
                .when().post();


        response.then().statusCode(201)
                .body("name", Matchers.is("techtorial"));
    }


    @Test
    public void createPet(){
        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="v2/pet";

        File petBody=new File("pet.json");
    Response response=given().accept(ContentType.JSON).accept(ContentType.JSON)
            .body(petBody)
            .when().post();

    response.then().statusCode(200)
            .body("name",Matchers.is("Hutch"));
    }

    @Test
    public void createPet2(){
        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="v2/pet";
        String reqBody="{\n" +
                "    \"id\": 112233,\n" +
                "    \"category\": {\n" +
                "        \"id\": 0,\n" +
                "        \"name\": \"string\"\n" +
                "    },\n" +
                "    \"name\": \"hatiko\",\n" +
                "    \"photoUrls\": [\n" +
                "        \"https://s3.amazon.com/mydoggiePhoto\"\n" +
                "    ],\n" +
                "    \"tags\": [\n" +
                "        {\n" +
                "            \"id\": 0,\n" +
                "            \"name\": \"string\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"status\": \"not available\"\n" +
                "}";
        Response response=given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(reqBody).when().post();

        response.then().statusCode(200).body("category.name",Matchers.equalTo("string"));
    }

    @Test
    public void createPet3(){
        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="v2/pet";

        PetPojo petBody=new PetPojo();
        petBody.setId(112233);
        petBody.setName("Doggie");
        petBody.setStatus("not available");
        Response response=given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(petBody).when().post();

        response.then().statusCode(200)
                .body("id",Matchers.is(112233))
                .and().body("status",Matchers.equalTo("not available"))
                .and().body("name",Matchers.equalTo("Doggie"));


    }
}
