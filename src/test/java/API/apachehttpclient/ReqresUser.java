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

public class ReqresUser {


    @Test
    public void verifyUser() throws URISyntaxException, IOException {
        HttpClient httpClient= HttpClientBuilder.create().build();

        //https://reqres.in/api/users/2
        URIBuilder uri=new URIBuilder();
        uri.setScheme("https").setHost("reqres.in").setPath("/api/users/2");

        HttpGet get=new HttpGet(uri.build());
        get.setHeader("Accept","application/json");

        HttpResponse response = httpClient.execute(get);
        Assert.assertEquals("Status code assertion failed", HttpStatus.SC_OK,response.getStatusLine().getStatusCode());

        Assert.assertTrue("Invalid content header",response.getEntity().getContentType().getValue().contains("application/json"));

            //we need to get dependency jackson data bind maven

        ObjectMapper objectMapper=new ObjectMapper();

      Map<String, Object> deserializedObject=objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String,Object>>(){});

        System.out.println(deserializedObject.keySet());
        System.out.println(deserializedObject.size());

        System.out.println(deserializedObject.get("data"));
        Map<String,Object> dataValue=(Map<String,Object>)deserializedObject.get("data");

//
//        System.out.println("User id is :"+dataValue.get("id"));
//        System.out.println("User name is :"+dataValue.get("first_name"));
//        System.out.println("User last name is: "+dataValue.get("last_name"));
//        System.out.println("User avatar is: "+dataValue.get("avatar"));
//        System.out.println("User email is: "+dataValue.get("email"));
//        System.out.println("========================================");

        Map<String,String> adValue=(Map<String,String>)deserializedObject.get("ad");
//
//        System.out.println("User company is :"+adValue.get("company"));
//        System.out.println("User url is :"+adValue.get("url"));
//        System.out.println("User text is: "+adValue.get("text"));

        for(String st:dataValue.keySet()){
            System.out.println("Users "+st+" is: "+dataValue.get(st));
        }

        for(String st:adValue.keySet()){
            System.out.printf("User's %s is %s\n",st,adValue.get(st));
        }

    }
}
