package API.apachehttpclient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Map;
import java.util.Set;

public class PetStore {

    String type="application/json";
        @Test
        public void getPet() throws URISyntaxException, IOException {
            HttpClient httpClient= HttpClientBuilder.create().build();

            URIBuilder uri=new URIBuilder();
            //petstore.swagger.io/v2
            uri.setScheme("https")
                    .setHost("petstore.swagger.io")
                    .setPath("/v2/pet/3");
            HttpGet get=new HttpGet(uri.build());
            get.setHeader("Accept",type);

            HttpResponse response = httpClient.execute(get);

            Assert.assertEquals(HttpStatus.SC_OK,response.getStatusLine().getStatusCode());
            Assert.assertTrue(response.getEntity().getContentType().getValue().contains(type));

            ObjectMapper objectMapper=new ObjectMapper();
          Map<String,Object> deserializedData= objectMapper.readValue(response.getEntity().getContent(),new TypeReference< Map<String,Object>>(){});

           Set<String> keys=deserializedData.keySet();
            System.out.println(keys);

            for(String key:keys){

                System.out.println("This is "+key+"-->" +deserializedData.get(key));
            }
            }

        }

