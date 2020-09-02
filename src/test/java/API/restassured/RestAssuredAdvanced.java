package API.restassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAssuredAdvanced {



    @Test
    public void getOrder(){
        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="v2/store/order";

        Response response=given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when().get("/{orderId}",55);
        System.out.println(response.path("petId").toString());;

        JsonPath jsonPath=response.jsonPath();
      //  System.out.println(jsonPath.getMap("$"));

        //$ root node
        Map<String,Object> responseMap=jsonPath.getMap("$");
        System.out.println(responseMap.get("shipDate"));

        response.then().statusCode(200);
    }


    @Test
    public void getPet(){
        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="v2/pet";

        Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .when().get("{petId}", 112233)
                .then().statusCode(200).extract().response();

        JsonPath jsonPath = response.jsonPath();
        Map<String,Object> category=jsonPath.getMap("category");
        System.out.println(category.get("name"));

        List<Map<String,Object>> tags = jsonPath.getList("tags");
        System.out.println(tags.get(0).get("name"));

    }

    @Test
    public void printEmails(){
        /*
        1. Send GET request to https://reqres.in/api/users
        2. Deserialize response body using JsonPath class
        3. get all emails of users
         */
        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="api/users";

        Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .when().get()
                .then().statusCode(200).extract().response();


        JsonPath jsonPath = response.jsonPath();

        List<Map<String,Object>> data=jsonPath.getList("data");

        data.forEach(f -> System.out.println(f.get("email")));
        System.out.println("===================FOR LOOP=======================");
        for(int i=0;i<data.size();i++){
            System.out.println(data.get(i).get("email"));
        }

    }
}
