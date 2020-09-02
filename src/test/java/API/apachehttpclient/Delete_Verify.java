package API.apachehttpclient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Properties;

public class Delete_Verify {

    @Test
    public void deleteVerify() throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        int petId = 1;
        // https://petstore.swagger.io/v2/pet
        uriBuilder.setScheme("https");
        uriBuilder.setHost("petstore.swagger.io");
        uriBuilder.setPath("v2/pet/"+petId);
        Properties properties = new Properties();
        properties.load(new FileInputStream(new File("configuration.properties")));
        String appJson = properties.getProperty("json");
        HttpDelete delete = new HttpDelete(uriBuilder.build());
        delete.setHeader("Accept",appJson);
        delete.setHeader("Content-Type",appJson);
        HttpResponse httpResponse = httpClient.execute(delete);
        Assert.assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
        Assert.assertEquals(appJson,httpResponse.getEntity().getContentType().getValue());
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> deleteDetails = objectMapper.readValue(httpResponse.getEntity().getContent(),
                new TypeReference<Map<String, Object>>(){});
        Assert.assertEquals(String.valueOf(petId), deleteDetails.get("message"));
        //send a get request to check if the pet was created
        uriBuilder.setPath("v2/pet/"+petId);
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept",appJson);
        httpGet.setHeader("Content-Type",appJson);
        httpResponse = httpClient.execute(httpGet);
        Assert.assertEquals(HttpStatus.SC_NOT_FOUND, httpResponse.getStatusLine().getStatusCode());
    }


}
