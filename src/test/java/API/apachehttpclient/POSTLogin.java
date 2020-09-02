package API.apachehttpclient;

import API.utils.PayloadConvertUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class POSTLogin {

@Test
    public void postAuthorization() throws URISyntaxException, IOException {
    //creating client
    HttpClient client= HttpClientBuilder.create().build();

    // build URI
    URIBuilder uriBuilder=new URIBuilder();
    uriBuilder.setScheme("https")
            .setHost("reqres.in")
            .setPath("/api/login");
    //construct a request
    HttpPost post=new HttpPost(uriBuilder.build());
    post.setHeader("Accept","application/json");


    //construct a request body parameter

    String requestBody= PayloadConvertUtil.generateStringFromResources("src\\test\\java\\API\\utils\\AuthorizationPayload.json");
    HttpEntity entity=new StringEntity(requestBody, ContentType.APPLICATION_JSON);
    post.setEntity(entity);

    HttpResponse response;
    //handling an unhandled exception
    try{
       response=client.execute(post);
    }catch (Exception e){
        e.printStackTrace();
        throw new RuntimeException();
    }

    Assert.assertEquals(HttpStatus.SC_OK,response.getStatusLine().getStatusCode());
    Assert.assertTrue(response.getEntity().getContentType().getValue().contains("application/json"));

    //response body deseriaization
    ObjectMapper objectMapper=new ObjectMapper();
    Map<String,String> token=objectMapper.readValue(response.getEntity().getContent(),
            new TypeReference<Map<String,String>>(){});

    //response body verification
    Assert.assertTrue(token.containsKey("token"));
}

}
