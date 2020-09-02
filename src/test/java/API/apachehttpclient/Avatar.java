package API.apachehttpclient;

import API.Pages.ReqresPage;
import Utils.BrowserUtils;
import Utils.Driver;
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
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Avatar {
        ReqresPage reqresPage=new ReqresPage();
    WebDriver driver= Driver.getDriver();

    @Test
    public void getAllPic() throws URISyntaxException, IOException {
        HttpClient httpClient= HttpClientBuilder.create().build();
    //https://reqres.in/api/users?per_page=21
        URIBuilder uri=new URIBuilder();
        uri.setScheme("https")
                .setHost("reqres.in")
                .setPath("/api/users")
                .setCustomQuery("per_page=12");
        HttpGet get=new HttpGet(uri.build());
        get.setHeader("Accept","application/json");

        HttpResponse response = httpClient.execute(get);

        Assert.assertEquals(HttpStatus.SC_OK,response.getStatusLine().getStatusCode());
        Assert.assertTrue(response.getEntity().getContentType().getValue().contains("application/json"));

        ObjectMapper objectMapper=new ObjectMapper();

       Map<String,Object> data= objectMapper.readValue(response.getEntity().getContent(),new TypeReference<Map<String, Object>>(){});

        System.out.println(data.get("data"));
        List dataValue=(List<Object>)data.get("data");
        List<String> avatarLinks=new ArrayList<>();
        Assert.assertEquals(data.get("per_page"),dataValue.size());
        for(int i=0;i<dataValue.size();i++){
            Map<String,Object>dataItem=(Map<String,Object>)dataValue.get(i);

        avatarLinks.add(dataItem.get("avatar").toString());
        }

       for(int i=0;i<avatarLinks.size();i++){
           BrowserUtils.waitForSec(1);
           driver.get(avatarLinks.get(i));
           Assert.assertTrue(reqresPage.avatarImage.isDisplayed());
           Assert.assertEquals(reqresPage.avatarImage.getAttribute("src"),avatarLinks.get(i));
       }

    }
}
