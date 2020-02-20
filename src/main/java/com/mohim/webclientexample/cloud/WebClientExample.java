package com.mohim.webclientexample.cloud;

import com.mohim.webclientexample.domain.Person;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@Component
public class WebClientExample {
    private static final Logger LOGGER = Logger
            .getLogger(WebClientExample.class.getName());

    private static final String BASE_URL = "http://localhost:8080";
    private static final String MOCK_GET_ENDPOINT = "/mock-get";
    private static final String MOCK_POST_ENDPOINT = "/mock-post";


    private WebClient webClient;


    public WebClientExample() {
        webClient = WebClient.builder()
                .baseUrl(BASE_URL)
                .build();
    }

    public Mono<String> sendGetRequestToMock() {
        return webClient
                .get()
                .uri(MOCK_GET_ENDPOINT)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> sendPostRequestToMock(Person person) {
        return webClient
                .post()
                .uri(MOCK_POST_ENDPOINT)
                .bodyValue(person)
                .retrieve()
                .bodyToMono(String.class);
    }


}
