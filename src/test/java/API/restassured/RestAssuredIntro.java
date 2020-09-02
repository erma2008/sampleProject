package API.restassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class RestAssuredIntro {
    @Before
    public void setUp(){
        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="api/users";
    }

    @Test
    public void restassuredGet(){
        RestAssured.baseURI="https://reqres.in";
        given().accept(ContentType.JSON)
            .when().get()
                .then().statusCode(200).contentType(ContentType.JSON)
                .body("page",equalTo(1));
    }

    @Test
    public void restassuredGetUser(){
    given().accept(ContentType.JSON)
            .when().get("/11")
            .then().statusCode(200)
            .and().body("data.first_name",equalTo("George"))
            .and().body("ad.text", Matchers.notNullValue())
            .log().body();
    }

    @Test
    public void getAllUsers(){
        given().accept(ContentType.JSON)
                .when().get()
                .then().statusCode(200)
                .and().body("data",hasSize(6));
    }

    @Test
    public void getAllUsers2(){
        given().accept(ContentType.JSON)
                .when().get()
                .then().statusCode(200)
                .and().body("data[1].last_name",equalTo("Weaver"));
    }


    @Test
    public void get12Users(){
        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="api/users";
        Map<String,Object> param=new HashMap<>();
        param.put("per_page",12);
        given().accept(ContentType.JSON).param("per_page",12)
                .when().get()
                .then().body("data",hasSize(12));
    }

    @Test
    public void getUserResponse(){

        //execute a get call and assign the response to a variable
        Response response= given().accept(ContentType.JSON)
                .when().get();

        // do verification
        response.then().statusCode(200).and()
                .body("data",hasSize(6));


    }


}
