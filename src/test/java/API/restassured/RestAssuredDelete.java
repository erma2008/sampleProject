package API.restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.when;

public class RestAssuredDelete {


    @Test
    public void deletePet(){
        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="v2/pet";
        //send a delete request to delete a pet with 112233
        Response response=when().delete("/112233");
        response.then().statusCode(200);


    }

    @Test
    public void deletePet2(){
        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="v2/pet";
        //send a delete request to delete a pet with 112233
        //when().request("DELETE","/112233").then().statusCode(200);
        when().request("DELETE","/{petId}",112233).then().statusCode(200);

    }


}
