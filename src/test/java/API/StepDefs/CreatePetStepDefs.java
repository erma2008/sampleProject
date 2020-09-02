package API.StepDefs;

import API.utils.PayLoadUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Properties;

import static API.utils.Constants.*;

public class CreatePetStepDefs {
    private HttpClient httpClient;
    private HttpResponse httpResponse;
    private  URIBuilder uriBuilder;
    private String json;
    private int petId;
    ObjectMapper objectMapper;




    @When("the user {string} a pet with id {int}")
    public void the_user_creates_a_pet(String action, Integer petId) throws IOException, URISyntaxException {
        this.petId = petId;
        httpClient = HttpClientBuilder.create().build();

        uriBuilder = new URIBuilder();
        // https://petstore.swagger.io/v2/pet
        uriBuilder.setScheme(HTTPS);
        uriBuilder.setHost("petstore.swagger.io");

        if (action.equalsIgnoreCase("creates")) {

        uriBuilder.setPath("v2/pet");

        Properties properties = new Properties();
        properties.load(new FileInputStream(new File("configuration.properties")));
        json = properties.getProperty("json");

        HttpPost httpPost = new HttpPost(uriBuilder.build());
        httpPost.setHeader("Accept", json);
        httpPost.setHeader("Content-Type", json);
        String requestBody = PayLoadUtil.getPetPayload(petId);
        HttpEntity entity = new StringEntity(requestBody);
        httpPost.setEntity(entity);

        //Send a POST request
        httpResponse = httpClient.execute(httpPost);
        Assert.assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
        Assert.assertEquals(json, httpResponse.getEntity().getContentType().getValue());
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> petDetails = objectMapper.readValue(httpResponse.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });
        Assert.assertEquals(petId, petDetails.get(ID));
        //send a get request to check if the pet was created
        uriBuilder.setPath("v2/pet/" + petId);
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept", json);
        httpGet.setHeader("Content-Type", json);
        httpResponse = httpClient.execute(httpGet);
    }else if(action.equalsIgnoreCase("delete")){

            uriBuilder.setPath("v2/pet/" + petId);

            HttpDelete delete = new HttpDelete(uriBuilder.build());
            objectMapper = new ObjectMapper();
            httpResponse = httpClient.execute(delete);
        }

    }

    @Then("the status code should be {int}")
    public void the_status_code_should_be(int statusCode) {
        Assert.assertEquals(statusCode, httpResponse.getStatusLine().getStatusCode());
        System.out.println(httpResponse.getEntity().getContentType().getValue().isEmpty());
        Assert.assertEquals(json,httpResponse.getEntity().getContentType().getValue());
    }

    @Then("pet is {string}")
    public void pet_is_created(String action) throws IOException, URISyntaxException {
        if(action.equalsIgnoreCase("created")) {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> petDetails = objectMapper.readValue(httpResponse.getEntity().getContent(),
                    new TypeReference<Map<String, Object>>() {
                    });
            Assert.assertEquals(petId, petDetails.get(ID));
            //send a get request to check if the pet was created
            uriBuilder.setPath("v2/pet/" + petId);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader("Accept", json);
            httpGet.setHeader("Content-Type", json);
            httpResponse = httpClient.execute(httpGet);
            Assert.assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
            Assert.assertEquals(json, httpResponse.getEntity().getContentType().getValue());
            Map<String, Object> petDetailsGet = objectMapper.readValue(httpResponse.getEntity().getContent(),
                    new TypeReference<Map<String, Object>>() {
                    });
            Assert.assertEquals(petId, petDetailsGet.get(ID));
            Assert.assertEquals("Tarzan", petDetailsGet.get(NAME));
        }else if(action.equalsIgnoreCase("deleted")){
            Assert.assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> deleteDetails = objectMapper.readValue(httpResponse.getEntity().getContent(),
                    new TypeReference<Map<String, Object>>(){});
            Assert.assertEquals(String.valueOf(petId), deleteDetails.get("message"));
            //send a get request to check if the pet was created
            uriBuilder.setPath("v2/pet/"+petId);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader("Accept",json);
            httpResponse = httpClient.execute(httpGet);
            Assert.assertEquals(HttpStatus.SC_NOT_FOUND, httpResponse.getStatusLine().getStatusCode());
        }


    }


//    @Then("pet is {string}")
//    public void pet_is(String action) throws IOException, URISyntaxException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        if (action.equalsIgnoreCase("created")) {
//            uriBuilder.setPath("v2/pet/" + petId);
//            HttpGet get = new HttpGet(uriBuilder.build());
//            httpResponse = httpClient.execute(get);
//            Map<String, Object> getPettDetails = objectMapper.readValue(
//                    httpResponse.getEntity().getContent(),
//                    new TypeReference<Map<String, Object>>(){});
//            Assert.assertEquals(petId, getPettDetails.get(ID));
//            Assert.assertEquals("Bomjik", getPettDetails.get(NAME));
//        } else if (action.equalsIgnoreCase("deleted")) {
//            HttpGet get = new HttpGet(uriBuilder.build());
//            httpResponse = httpClient.execute(get);
//            Map<String, Object> petDetails = objectMapper.readValue(
//                    httpResponse.getEntity().getContent(),
//                    new TypeReference<Map<String, Object>>(){});
//            Assert.assertEquals(HttpStatus.SC_NOT_FOUND, httpResponse.getStatusLine().getStatusCode());
//            Assert.assertEquals("Pet not found", petDetails.get("message"));
//        }
//    }





}
