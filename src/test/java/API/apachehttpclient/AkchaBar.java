package API.apachehttpclient;

import API.pojo.AkchaBarPojo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class AkchaBar {

    @Test
    public void getRates() throws URISyntaxException, IOException {
        //http://rates.akchabar.kg/get.json
        HttpClient httpClient= HttpClientBuilder.create().build();

        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("http")
                .setHost("rates.akchabar.kg")
                .setPath("/get.json");

        HttpGet get=new HttpGet(uriBuilder.build());
        get.setHeader("Accept","application/json");

        HttpResponse response = httpClient.execute(get);

        ObjectMapper objectMapper=new ObjectMapper();

        AkchaBarPojo akchaBarPojo= objectMapper.readValue(
                response.getEntity().getContent(), AkchaBarPojo.class);

        System.out.println("USD rate is: "+ akchaBarPojo.getRates().getUsd());
        System.out.println("EURO rate is: "+akchaBarPojo.getRates().getEuro());
        System.out.println("RUBLE rate is: "+akchaBarPojo.getRates().getRuble());
        System.out.println("TENGE rate is: "+akchaBarPojo.getRates().getTenge());
        System.out.println("BTC rate is: "+akchaBarPojo.getRates().getBtc());

    }
}
