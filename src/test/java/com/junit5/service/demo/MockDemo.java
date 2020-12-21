package com.junit5.service.demo;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class MockDemo {
    private static WireMockServer wireMockServer;
    @BeforeAll
    public static void beforeAll(){
        wireMockServer = new WireMockServer(wireMockConfig().port(8089)); //No-args constructor will start on port 8080, no HTTPS
        wireMockServer.start();
        configureFor("localhost",8089);
    }

    @Test
    public void stubMock() {
        stubFor(get(urlEqualTo("/my/resource"))
                .withHeader("Accept", equalTo("text/xml"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("<response>hogwarts</response>")));
        try {
            Thread.sleep(500000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void easyMock() {
        stubFor(get(urlEqualTo("/my/resource"))
                .withHeader("Accept", equalTo("text/xml"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("<response>hogwarts</response>")));
        try {
            Thread.sleep(10000);
            reset();
            stubFor(get(urlEqualTo("/my/resource"))
                    .withHeader("Accept", equalTo("text/xml"))
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "text/xml")
                            .withBody("<response>名企定向4期</response>")));
            Thread.sleep(500000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void proxyMock(){
        stubFor(get(urlMatching(".*")).atPriority(10)
                .willReturn(aResponse().proxiedFrom("https://ceshiren.com")));

        try {
            stubFor(get(urlEqualTo("/categories_and_latest")).atPriority(10)
                    .willReturn(aResponse().withBody(Files.readAllBytes(Paths.get(MockDemo.class.getResource("/mock.json").getPath())))));
            Thread.sleep(50000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
