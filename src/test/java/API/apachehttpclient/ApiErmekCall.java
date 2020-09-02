package API.apachehttpclient;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;


public class ApiErmekCall {

    @Test
            public void eeeApiCall() throws URISyntaxException, IOException {
        HttpClient httpClient= HttpClientBuilder.create().build();

        URIBuilder uri=new URIBuilder();
        uri.setScheme("https");
        uri.setHost("reqres.in");
        uri.setPath("/api/users?page=2");

        HttpGet httpGet=new HttpGet(uri.build());
        httpGet.setHeader("Accept","application/json");

        HttpResponse response=httpClient.execute(httpGet);
        Assert.assertEquals("Status code assertion failed", HttpStatus.SC_OK,response.getStatusLine().getStatusCode());

        Assert.assertEquals("Invalid content","application/json; charset=utf-8",response.getEntity().getContentType().getValue());
    }


}
