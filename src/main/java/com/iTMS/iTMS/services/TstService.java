package com.iTMS.iTMS.services;

import com.iTMS.iTMS.WebExlib.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URI;

@Service
public class TstService {
    @Value("${MY_DEVELOPER_TOKEN}")
    private String token;

    @PostConstruct
    private void init() throws MalformedURLException {
        Spark spark = Spark.builder()
                .baseUrl(URI.create("https://webexapis.com/v1"))
                .accessToken(token)
                .build();

//        Person person = new Person();
//        person = spark
//                .people()
//                .queryParam()
//                .get();
//        System.out.println(person.toString());

//        spark.webhooks().
    }

    public String getToken() {
        return token;
    }
}
