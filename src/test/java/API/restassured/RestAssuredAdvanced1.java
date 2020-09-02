package API.restassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAssuredAdvanced1 {
    private static final String AUTH = "X-Auth-Token";
    private static final String TOKEN = "0781898207d84546abfea4f2c29ef6e5";

    @Test
    public void getTeam(){
        //http://api.football-data.org/v2/competitions/2000/teams
        RestAssured.baseURI="https://api.football-data.org";
        RestAssured.basePath="/v2/competitions/2000/teams";

        Response response= given().accept(ContentType.JSON).header(AUTH,TOKEN)
                .when().request("GET").then().statusCode(200).extract().response();
        //find()
        //it == this
           Map<String,Object> portugal= response.path("teams.find {it.name=='Portugal'}");

        Assert.assertEquals(portugal.get("name"),"Portugal");

    }

    @Before
    public void setUP(){
        RestAssured.baseURI="https://api.football-data.org";
        RestAssured.basePath="/v2/competitions/2000/teams";
    }

    @Test
    public void getCountryID(){
        //2169
        //http://api.football-data.org/v2/competitions/2000/teams


        Response response= given().accept(ContentType.JSON).header(AUTH,TOKEN)
                .when().request("GET").then().statusCode(200).extract().response();
        //find()
        //it == this
        Map<String,Object> id= response.path("teams.area.find {it.id==2169}");
        String country1=id.get("name").toString();

       JsonPath jsonPath =response.jsonPath();

       List<Map<String,Object>> teams=jsonPath.getList("teams");

       for(Map<String,Object>map:teams){
           Map<String,Object> area=(Map<String,Object>) map.get("area");
           if((int)area.get("id")==2169){
               System.out.println(map.get("name"));
           }
       }
    }

    @Test
    public void findAll(){
        Response response= given().accept(ContentType.JSON).header(AUTH,TOKEN)
                .when().request(Method.GET).then().statusCode(200).extract().response();


       List<String>countryNames= response.path("teams.findAll {it.founded < 1900}.name");
        System.out.println(countryNames);
    }

    @Test
    public void findCountryCode(){
        Response response= given().accept(ContentType.JSON).header(AUTH,TOKEN)
                .when().request(Method.GET).then().statusCode(200).extract().response();


        List<String>countryNames= response.path("teams.findAll {it.name.startsWith('S')}.name");
        System.out.println(countryNames);
    }

    @Test
    public void findMax(){
        Response response= given().accept(ContentType.JSON).header(AUTH,TOKEN)
                .when().request(Method.GET).then().statusCode(200).extract().response();
        System.out.println(response.path("teams.max{it.id}.area.name").toString());
        System.out.println(response.path("teams.min{it.id}.area.name").toString());

    }

    @Test
    public void collect(){
        Response response= given().accept(ContentType.JSON).header(AUTH,TOKEN)
                .when().request(Method.GET).then().statusCode(200).extract().response();

        int sumOfId=response.path("teams.collect{it.id}.sum()");
        System.out.println(sumOfId);

        List<String> names=response.path("teams.collect{it.name}");
        System.out.println(names);
        List<String> webpages=response.path("teams.collect{it.website}");
        System.out.println(webpages);
    }
        @Test
    public void genericDataType(){
            Response response= given().accept(ContentType.JSON).header(AUTH,TOKEN)
                    .when().request(Method.GET).then().statusCode(200).extract().response();

           Map<String,?>competition= response.path("competition");

           Map<String,?> area=(Map<String,?>)competition.get("area");

            System.out.println(area.get("name"));
        }
}
