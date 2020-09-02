package API.restassured;

import API.pojo.OrderPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SendingPostOrder {


       @Test
    public void orederingPet(){
        //https://petstore.swagger.io/#/store/placeOrder
        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="/v2/store/order";
        String postBody="{\n" +
                "  \"id\": 112233,\n" +
                "  \"petId\": 0,\n" +
                "  \"quantity\": 1,\n" +
                "  \"shipDate\": \"2020-03-29T16:56:12.015Z\",\n" +
                "  \"status\": \"placed\",\n" +
                "  \"complete\": true\n" +
                "}";

        Response response= given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .when().body(postBody).post();
        response.then().statusCode(200);

    }
    @Test
    public void orederingPetPojo(){
        //https://petstore.swagger.io/#/store/placeOrder
        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="/v2/store/order";

        OrderPojo order=new OrderPojo();
        order.setId(55);
        order.setPetId(112233);
        order.setShipDate("2020-03-29T16:56:12.015+0000");
        order.setComplete(true);

        Response response= given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(order).when().request("POST");
        response.then().statusCode(200)
        .and().body("petId", Matchers.is(112233));

    }
    @Test
    public void orederingPetMap(){
        //https://petstore.swagger.io/#/store/placeOrder
        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="/v2/store/order";

       Map<String,Object> reqMap=new HashMap<>();
        reqMap.put("id",99);
        reqMap.put("petId",112233);
        reqMap.put("complete",false);
        reqMap.put("quantity",5);
        reqMap.put("shipDate","2020-03-29T16:56:12.015+0000");
        reqMap.put("status","not placed");

        Response response= given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(reqMap).when().request("POST");
        response.then().statusCode(200)
                .and().body("petId", Matchers.is(112233));

    }

}
