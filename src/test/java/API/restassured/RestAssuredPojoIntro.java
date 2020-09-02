package API.restassured;

import API.pojo.AllCatsFactsPojo;
import API.pojo.PetPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredPojoIntro {

    @Test
    public void getPet(){
       Response response= given().accept(ContentType.JSON)
                .when().get("https://petstore.swagger.io/v2/pet/112233");
       response.then().statusCode(200);
        PetPojo pet=response.getBody().as(PetPojo.class);

        Assert.assertEquals(112233,pet.getId());

    }

    @Test
    public void getcatfactsNotByAlexWohlbruck(){
        Response response= given().accept(ContentType.JSON)
                .when().get("https://cat-fact.herokuapp.com/facts/");
        response.then().statusCode(200);

        AllCatsFactsPojo facts=response.getBody().as(AllCatsFactsPojo.class);

        for(int i=0; i<facts.getAll().size();i++){
            if(facts.getAll().get(i).getUser() != null) {
                if (!facts.getAll().get(i).getUser().getName().getLast().equalsIgnoreCase("Wohlbruck")) {
                    System.out.println(facts.getAll().get(i).getText());
                    System.out.println("===============================");
                }
            }
        }

    }
}
