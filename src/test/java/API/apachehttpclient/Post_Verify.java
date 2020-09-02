package API.apachehttpclient;

import API.utils.PayLoadUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Properties;

public class Post_Verify {

    int petId = 112233;
    @Test
    public void createAPet() throws IOException, URISyntaxException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        // https://petstore.swagger.io/v2/pet
        uriBuilder.setScheme("https");
        uriBuilder.setHost("petstore.swagger.io");
        uriBuilder.setPath("v2/pet");
        Properties properties = new Properties();
        properties.load(new FileInputStream(new File("configuration.properties")));
        String appJson = properties.getProperty("json");
        HttpPost httpPost = new HttpPost(uriBuilder.build());
        httpPost.setHeader("Accept", appJson);
        httpPost.setHeader("Content-Type", appJson);
        String requestBody = PayLoadUtil.getPetPayload(petId);
        HttpEntity entity = new StringEntity(requestBody);
        httpPost.setEntity(entity);
        HttpResponse httpResponse = httpClient.execute(httpPost);
        Assert.assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
        Assert.assertEquals(appJson,httpResponse.getEntity().getContentType().getValue());

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> petDetails = objectMapper.readValue(httpResponse.getEntity().getContent(),
                new TypeReference<Map<String, Object>>(){});
        Assert.assertEquals(petId, petDetails.get("id"));
        //send a get request to check if the pet was created
        uriBuilder.setPath("v2/pet/"+petId);
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept",appJson);
        httpGet.setHeader("Content-Type",appJson);
        httpResponse = httpClient.execute(httpGet);
        Assert.assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
        Assert.assertEquals(appJson,httpResponse.getEntity().getContentType().getValue());
        Map<String, Object> petDetailsGet = objectMapper.readValue(httpResponse.getEntity().getContent(),
                new TypeReference<Map<String, Object>>(){});
        Assert.assertEquals(petId, petDetailsGet.get("id"));
        Assert.assertEquals("Tarzan",petDetailsGet.get("name"));
    }


    }

