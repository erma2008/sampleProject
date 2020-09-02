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
import java.util.List;
import java.util.Map;

public class GetListOfUsers {

    @Test
    public void countUsers() throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https")
                .setHost("reqres.in")
                .setPath("api/users")
                .setCustomQuery("per_page=12");
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept", "application/json");
        HttpResponse response = httpClient.execute(httpGet);
        System.out.println(response.getStatusLine().getStatusCode());
        System.out.println(response.getEntity().getContentType().getValue());
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        Assert.assertEquals("Accept", "application/json; charset=utf-8", response.getEntity().getContentType().getValue());
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String, Object> deserilized=objectMapper.readValue(response.getEntity().getContent(),new TypeReference<Map<String,Object>>(){});

        List<Object> listOfObject=(List<Object>) deserilized.get("data");
        System.out.println(deserilized.size());
        Assert.assertEquals(deserilized.get("per_page"),listOfObject.size());

      //  Map<String,Object>dataValue=(Map<String,Object>)deserilized.get("data");
        int sum=0;
        for (int i = 0; i < listOfObject.size(); i++){
            Map<String, Object> dataItem = (Map<String, Object>)listOfObject.get(i);
            System.out.println(dataItem.get("first_name"));

            sum=+(int) dataItem.get("id");
        }
        System.out.println(sum);

    }
}
