package API.apachehttpclient;

import API.pojo.All;
import API.pojo.AllCatsFactsPojo;
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

import static org.junit.Assert.assertEquals;

public class APIallFacts {
    @Test
    public void getFacts() throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        URIBuilder uri = new URIBuilder();
        uri.setScheme("https").setHost("cat-fact.herokuapp.com").setPath("facts");
        HttpGet httpGet = new HttpGet(uri.build());
        httpGet.setHeader("Accept", "application/json");

        int notCatFacts=0;
        HttpResponse response = httpClient.execute(httpGet);
        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        Assert.assertTrue(response.getEntity().getContentType().getValue().startsWith("application/json"));
        ObjectMapper objectMapper = new ObjectMapper();
        AllCatsFactsPojo catFacts = objectMapper.readValue(response.getEntity().getContent(), AllCatsFactsPojo.class);
        List<All> allFactsList = catFacts.getAll();
        int totalFactsCount = 0;
        for(All fact: allFactsList) {
            fact.getText();
            totalFactsCount++;
            // upvotes more than 3
            if(fact.getUpvotes() > 3){
                System.out.println("Upvotes is: "+fact.getUpvotes()+"   Fact: "+ fact.getText());
            }

            if(!fact.getText().toLowerCase().contains("cat")
                    & !fact.getText().toLowerCase().contains("Kittens")
                    & !fact.getText().contains("Mouser") & !fact.getText().contains("breed")
                    & !fact.getText().contains("tabbies") & !fact.getText().contains("domestic")
                    & !fact.getText().contains("Chartreux") & !fact.getText().contains("bezoar")
            ){
                notCatFacts++;
                System.out.println("\nNot 'cat' fact is: "+ fact.getText());
            }
        }
        System.out.println("----------------------------------------");
        System.out.println("Total amount of facts is: "+ totalFactsCount);
        System.out.println("NOT cat Facts "+notCatFacts);
    }
}

