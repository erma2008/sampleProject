package API.apachehttpclient;

import API.utils.PayLoadUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
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

public class POSTIntro {


    @Test
    public void postPet() throws URISyntaxException, IOException {
        HttpClient httpClient= HttpClientBuilder.create().build();

        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("petstore.swagger.io");
        uriBuilder.setPath("/v2/pet");
        //======================================
        Properties properties = new Properties();
        properties.load(new FileInputStream(new File("configuration.properties")));
        String json =properties.getProperty("json");
        //=====================================

        HttpPost post=new HttpPost(uriBuilder.build());
        post.setHeader("Accept",json);
        post.setHeader("Content-Type",json);


        int petId = 1234567;
        String reqBody= PayLoadUtil.erma(petId);

        HttpEntity entity=new StringEntity(reqBody);
        post.setEntity(entity);

        HttpResponse response = httpClient.execute(post);

        System.out.println(response.getStatusLine().getStatusCode());

        Assert.assertEquals(HttpStatus.SC_OK,response.getStatusLine().getStatusCode());
        Assert.assertTrue(response.getEntity().getContentType().getValue().contains(json));
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> petDetails = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>(){});
        Assert.assertEquals(petId, petDetails.get("id"));
    }





    @Test
        public void createUser() throws URISyntaxException, IOException {
            HttpClient client = HttpClientBuilder.create().build();
            URIBuilder uriBuilder = new URIBuilder();
           // https://reqres.in/api/users
            uriBuilder.setScheme("https");
            uriBuilder.setHost("reqres.in");
            uriBuilder.setPath("/api/users");
            HttpPost httpPost = new HttpPost(uriBuilder.build());
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-Type", "application/json");
            String name = "borssok";
            String job = "bratoka";
            String requestBody = PayLoadUtil.getUserPayLoad(name, job);
            HttpEntity entity = new StringEntity(requestBody);//polymorphism
            httpPost.setEntity(entity); //setting request body
            HttpResponse response = client.execute(httpPost);
            Assert.assertEquals(HttpStatus.SC_CREATED, response.getStatusLine().getStatusCode());
            Assert.assertTrue(response.getEntity().getContentType().getValue().contains("application/json"));
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> details = mapper.readValue(response.getEntity().getContent(),
                    new TypeReference<Map<String, String>>() {
                    });
            Assert.assertEquals(name, details.get("name"));
            Assert.assertEquals(job, details.get("job"));
        }
        }
