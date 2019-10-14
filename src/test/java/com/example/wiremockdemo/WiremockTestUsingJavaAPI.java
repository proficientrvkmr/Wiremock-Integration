package com.example.wiremockdemo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWireMock
public class WiremockTestUsingJavaAPI {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void helloWireMock_Test() {
        stubFor(get(urlEqualTo("/api/hello"))
                .willReturn(aResponse().withStatus(HttpStatus.OK.value()).withBody("Hi Wiremock !!")));

        ResponseEntity responseEntity = restTemplate.getForEntity("http://localhost:8080/api/hello", String.class);
        Assert.assertEquals("Hi Wiremock !!", responseEntity.getBody().toString());
    }

    @Test
    public void helloWireMock2_Test() {

        ResponseEntity responseEntity = restTemplate.getForEntity("http://localhost:8080/api/mytest", String.class);
        Assert.assertEquals("My Test", responseEntity.getBody().toString());
    }
}
