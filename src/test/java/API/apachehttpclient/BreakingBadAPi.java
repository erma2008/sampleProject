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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BreakingBadAPi {



    @Test
    public void getNames() throws URISyntaxException, IOException {
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

        List<Map<String, Object>> characterDetails = objectMapper.readValue(response.getEntity().getContent(), new TypeReference<List<Map<String, Object>>>() {
        });

        List<String> aliveChars = new ArrayList<>();
        for (int i = 0; i < characterDetails.size(); i++) {
            if (characterDetails.get(i).get("status").equals("Alive")) {
                aliveChars.add((String) characterDetails.get(i).get("name"));
                System.out.println(characterDetails.get(i).get("name"));
            }
        }

        Map<String,Integer> nameAge= new HashMap<>();
        for (Map<String,Object> map: characterDetails) {
            if(!map.get("birthday").equals("Unknown")){
                String birthday =((String)map.get("birthday"));
                String birthYear=birthday.substring(birthday.length()-4);
                int age=2020-Integer.parseInt(birthYear);
                nameAge.put(map.get("name").toString(),age);

            }
        }
        System.out.println(nameAge);


    }


    }

