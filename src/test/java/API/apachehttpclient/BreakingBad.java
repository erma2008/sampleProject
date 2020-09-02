package API.apachehttpclient;

import API.pojo.BreakingBadPojo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class BreakingBad {

    @Test
    public void serializeBreakingBad() throws IOException, URISyntaxException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        //https://breakingbadapi.com/api/characters
        uriBuilder.setScheme("https")
                .setHost("breakingbadapi.com")
                .setPath("/api/characters");

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept", "application/json");
        HttpResponse response = httpClient.execute(httpGet);

        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        Assert.assertTrue(response.getEntity().getContentType().getValue().contains("application/json"));
        ObjectMapper objectMapper = new ObjectMapper();

       // CatFactsPojo breakingBad =objectMapper.readValue(response.getEntity().getContent(), BreakingBadPojo.class);

        File file=new File("breakingbad.json");

        BreakingBadPojo breakingBadPojo=new BreakingBadPojo();

        objectMapper.writeValue(file,breakingBadPojo);


    }
}
