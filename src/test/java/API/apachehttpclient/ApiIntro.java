package API.apachehttpclient;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class ApiIntro {

    @Test
    public void firstApiCall() throws URISyntaxException, IOException {
        /*

2. Construct a request:
	a. an http method(get, post,put delete etc)

		- Specify schema
		- Specify host
		- Specify path
		- Specify query params (if needed)
		- Specify headers
		- Specify body (if needed)
4. Execute the API call
         */

    //   1. Construct http client
        HttpClient httpClient= HttpClientBuilder.create().build();

        //b. Build an EndPoint
        URIBuilder uri=new URIBuilder();
        uri.setScheme("https");
        uri.setHost("petstore.swagger.io");
        uri.setPath("v2/pet/112233");

        //construct a get method
        HttpGet httpGet=new HttpGet(uri.build());
        httpGet.setHeader("Accept","application/json");

        //execute get request
        HttpResponse response=httpClient.execute(httpGet);

        System.out.println("Status code of my first api call "+
                response.getStatusLine().getStatusCode());
        // status code verification
        Assert.assertEquals("Status code assertion failed", HttpStatus.SC_OK,response.getStatusLine().getStatusCode());

//        System.out.println(response.getEntity().getContentType().getValue());

        //content type verification
        Assert.assertEquals("Invalid content type header","application/json",response.getEntity().getContentType().getValue());

    }


    @Test
    public void getListOfUsers() throws URISyntaxException, IOException {
        String applicationJson="application/json";
        //building Http client
        HttpClient httpClient= HttpClientBuilder.create().build();
        //building endpoint
        URIBuilder uri=new URIBuilder();
        uri.setScheme("https");
        uri.setHost("reqres.in");
        uri.setPath("/api/users");
        uri.setCustomQuery("page=2");
        //building http method request
        HttpGet httpGet=new HttpGet(uri.build());
        httpGet.setHeader("Accept",applicationJson);
        httpGet.setHeader("Accept",ContentType.APPLICATION_JSON.toString());

        HttpResponse response=httpClient.execute(httpGet);
        Assert.assertEquals("Status code assertion failed", HttpStatus.SC_OK,response.getStatusLine().getStatusCode());

        Assert.assertTrue("Invalid content",response.getEntity().getContentType().getValue().contains(applicationJson));
    }

    @Test
    public void factAboutCat() throws URISyntaxException, IOException {
        //https://alexwohlbruck.github.io/cat-facts/docs/

        HttpClient httpClient=HttpClientBuilder.create().build();

        URIBuilder uri=new URIBuilder();
        uri.setScheme("https").setHost("alexwohlbruck.github.io").setPath("/cat-facts/docs");

        HttpGet get=new HttpGet(uri.build());
        get.setHeader("Accept","application/json");

        HttpResponse response = httpClient.execute(get);

        Assert.assertEquals(HttpStatus.SC_OK,response.getStatusLine().getStatusCode());
        Assert.assertFalse(response.getEntity().getContentType().getValue().contains("application/json"));
    }

    @Test
    public void herokuAppFacts() throws URISyntaxException, IOException {
        //https://cat-fact.herokuapp.com/facts
        HttpClient httpClient=HttpClientBuilder.create().build();

        URIBuilder uri=new URIBuilder();
        uri.setScheme("https");
        uri.setHost("cat-fact.herokuapp.com");
        uri.setPath("/facts");

        HttpGet get=new HttpGet(uri.build());
        get.setHeader("Accept","application/json");

        HttpResponse response = httpClient.execute(get);


        Assert.assertEquals(HttpStatus.SC_OK,response.getStatusLine().getStatusCode());
        Assert.assertTrue(response.getEntity().getContentType().getValue().startsWith("application/json"));
    }
@Test
    public void findByStatusPetStore() throws URISyntaxException, IOException {
        HttpClient httpClient=HttpClientBuilder.create().build();
        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("https")
                .setHost("petstore.swagger.io")
                .setPath("/v2/pet/findByStatus")
                .setCustomQuery("status=available");
        HttpGet httpGet=new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept","application/json");
        HttpResponse response=httpClient.execute(httpGet);



    Assert.assertEquals(HttpStatus.SC_OK,response.getStatusLine().getStatusCode());
        Assert.assertTrue(response.getEntity().getContentType().getValue().startsWith("application/json"));}

}
