package API.apachehttpclient;

import API.pojo.*;
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
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class SerializationIntro {

    @Test
    public void serialize() throws IOException {
        Rates rates=new Rates();

        rates.setBtc("10");
        rates.setEuro("15");
        rates.setUsd("25");
        rates.setRuble("5");
        rates.setTenge("2");

        System.out.println(rates.getEuro());
        System.out.println(rates.getBtc());
        System.out.println(rates.getTenge());


        //serialize java object to json
        ObjectMapper objectMapper=new ObjectMapper();
        File file =new File("rates.json");

        //might throw an exception,
        // we need to declare exception in method signature
        objectMapper.writeValue(file,rates);

    }

    @Test
    public void serialize2() throws IOException {
        UserPojo userPojo=new UserPojo();

        Ad ad=new Ad();
        Data data=new Data();
        data.setFirst_name("Borsok");
        data.setLast_name("MayTokoch");
        data.setAvatar("www.amazon.com/s3/image");
        data.setEmail("borsok@gmail.com");
        data.setId(140);


        ad.setCompany("NAN.inc");
        ad.setText("THE BEST NAN IN THE WORLD");
        ad.setUrl("www.nan.com");
        userPojo.setAd(ad);
        userPojo.setData(data);

        ObjectMapper objectMapper=new ObjectMapper();

        File file=new File("borsok.json");

        objectMapper.writeValue(file,userPojo);

    }

    @Test
    public void serializePet() throws IOException, URISyntaxException {

        HttpClient httpClient= HttpClientBuilder.create().build();

        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("petstore.swagger.io");
        uriBuilder.setPath("/v2/pet");


        HttpPost post=new HttpPost(uriBuilder.build());
//        post.setHeader("Accept","application/json");
        post.setHeader("Content-Type","application/json");

        PetPojo petPojo=new PetPojo();
        petPojo.setName("Hunter");
        petPojo.setStatus("GOne");
        petPojo.setId(123);

        Category category=new Category();
        category.setName("Sobaka");
        category.setId(123);

        petPojo.setCategory(category);

        List<String> photoUrls=new ArrayList<>();
        photoUrls.add("www.pictures.com");

        petPojo.setPhotoUrls(photoUrls);

       List<Tags> tagList=new ArrayList<>();
            Tags tags=new Tags();
        tags.setName("tagName");
        tags.setId(123);
        tagList.add(tags);
        petPojo.setTags(tagList);



    ObjectMapper objectMapper=new ObjectMapper();

    File file=new File("pet.json");

    objectMapper.writeValue(file,petPojo);

    String petJson=objectMapper.writeValueAsString(petPojo);
        System.out.println(petJson);

        HttpEntity entity=new StringEntity(petJson);

        HttpResponse response = httpClient.execute(post);

        Assert.assertEquals(HttpStatus.SC_OK,response.getStatusLine().getStatusCode());


    }

    @Test
    public void serPet() throws IOException, URISyntaxException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https")
                .setHost("breakingbadapi.com")
                .setPath("api/characters");
        HttpPost httpPost=new HttpPost(uriBuilder.build());
        httpPost.setHeader("Accept","application/json");
        HttpResponse response=httpClient.execute(httpPost);
        PetPojo petPojo=new PetPojo();
        Category category=new Category();
        category.setId(7);
        category.setName("poping");
        List<String> photoUR=new ArrayList();
        photoUR.add("www.pivture.com");
        List<Tags> tagsList=new ArrayList();
        Tags tags=new Tags();
        tags.setName("bobo");
        tags.setId(122) ;
        tagsList.add(tags);
        petPojo.setName("Dudu");
        petPojo.setStatus("home boy");
        petPojo.setId(333);
        petPojo.setCategory(category);
        ObjectMapper objectMapper=new ObjectMapper();
        File file=new File("pet.json");
        objectMapper.writeValue(file,petPojo);
    }
    }

