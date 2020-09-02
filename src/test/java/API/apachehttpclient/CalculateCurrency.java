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

public class CalculateCurrency {

    @Test
    public void getUser() throws URISyntaxException, IOException {
        double current$, currentEuro, currentBTC, currentRuble;
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uri = new URIBuilder();
        uri.setScheme("http").setHost("rates.akchabar.kg/").setPath("get.json");
        HttpGet get = new HttpGet(uri.build());
        get.addHeader("Accept", "application/json");
        get.addHeader("Content-Type", "application/json");
        HttpResponse response = client.execute(get);
        ObjectMapper objectMapper = new ObjectMapper();
        AkchaBarPojo userDetails = objectMapper.readValue(response.getEntity().getContent(), AkchaBarPojo.class);
        System.out.println(userDetails.getRates().getUsd());
        System.out.println(userDetails.getRates().getEuro());
        System.out.println(userDetails.getRates().getBtc());
        System.out.println(userDetails.getRates().getRuble());
        System.out.println(userDetails.getRates().getTenge());
        current$ = Double.parseDouble(userDetails.getRates().getUsd());
        currentEuro = Double.parseDouble(userDetails.getRates().getEuro());
        currentBTC = Double.parseDouble(userDetails.getRates().getBtc());
        currentRuble = Double.parseDouble(userDetails.getRates().getRuble());
        System.out.println("Your 100 US dollars will be " + CurrencyConverter(100, current$) + " soms");
        System.out.println("Your 245 Euros will be " + CurrencyConverter(245, currentEuro) + " soms");
        System.out.println("Your 67 Bitcoins will be " + CurrencyConverter(67, currentBTC) + " soms");
        System.out.println("Your million rubles will be " + CurrencyConverter(1000000, currentRuble) + " soms");
    }

    private String CurrencyConverter(int i, double current$) {
        return ""+(i*current$);

    }
}