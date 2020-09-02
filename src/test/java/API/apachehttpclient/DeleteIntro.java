package API.apachehttpclient;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;


public class DeleteIntro {

    @Test
    public void deleteUser() throws URISyntaxException, IOException {
        //create a client
        HttpClient httpClient= HttpClientBuilder.create().build();

        //build uri
        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("https")
                .setHost("reqres.in")
                .setPath("/api/user/2");

        //construct method
        HttpDelete delete=new HttpDelete(uriBuilder.build());
        HttpResponse response = httpClient.execute(delete);


        assertEquals(HttpStatus.SC_NO_CONTENT,response.getStatusLine().getStatusCode());

    }

}
