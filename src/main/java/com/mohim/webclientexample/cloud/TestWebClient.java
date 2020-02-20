package com.mohim.webclientexample.cloud;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@Component
public class TestWebClient {
    private static final Logger LOGGER = Logger
            .getLogger(TestWebClient.class.getName());

    private static final String BASE_URL = "http://localhost:8080";
    private static final String MOCK_ENDPOINT = "/mock-get";


    private WebClient webClient;


    public TestWebClient() {
        webClient = WebClient.builder()
                .baseUrl(BASE_URL)
                .build();
    }

    public Mono<String> sendGetRequestToMock() {
        return webClient
                .get()
                .uri(MOCK_ENDPOINT)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(String.class);
    }


}
