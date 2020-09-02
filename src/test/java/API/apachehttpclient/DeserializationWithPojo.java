package API.apachehttpclient;

import API.pojo.CatFactsPojo;
import API.pojo.UserPojo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;


public class DeserializationWithPojo {


    @Test
    public void getUser() throws URISyntaxException, IOException {
        HttpClient httpClient= HttpClientBuilder.create().build();

        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("https")
                .setHost("reqres.in")
                .setPath("api/users/5");

        HttpGet get=new HttpGet(uriBuilder.build());
            get.setHeader("Accept","applicatin.json");

        HttpResponse response = httpClient.execute(get);

        ObjectMapper objectMapper=new ObjectMapper();
       UserPojo userDetails= objectMapper.readValue(
                response.getEntity().getContent(),
               UserPojo.class);
        System.out.println(userDetails.getData().getFirst_name());


        Assert.assertEquals("Charles",userDetails.getData().getFirst_name());
        Assert.assertTrue(userDetails.getData().getLast_name().equals("Morris"));
    }






    @Test
    public void getCatfacts() throws URISyntaxException, IOException {

        //https://cat-fact.herokuapp.com/facts/58e008ad0aac31001185ed0c
        HttpClient httpClient=HttpClientBuilder.create().build();

        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("https")
                .setHost("cat-fact.herokuapp.com")
                .setPath("/facts/58e008ad0aac31001185ed0c");

        HttpGet get=new HttpGet(uriBuilder.build());
        get.setHeader("Accept","application/json");

        HttpResponse response = httpClient.execute(get);

        ObjectMapper objectMapper=new ObjectMapper();

        CatFactsPojo catFacts= objectMapper.readValue(
                response.getEntity().getContent(), CatFactsPojo.class);

        System.out.println(catFacts.getText());


    }
}
