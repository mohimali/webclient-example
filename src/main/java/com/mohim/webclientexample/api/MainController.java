package com.mohim.webclientexample.api;


import com.mohim.webclientexample.cloud.TestWebClient;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final TestWebClient testWebClient;

    @GetMapping("/testSendGetRequestToMock")
    public Mono<String> test(){
        return testWebClient.sendGetRequestToMock();
    }

    @GetMapping("/notes")
    public String notes() {

        WebClient client1 = WebClient.create();
        WebClient client2 = WebClient.create("http://localhost:8080");
        WebClient client3 = WebClient.builder()
                .baseUrl("http://localhost:8080")
                .defaultCookie("cookieTest", "cookietest")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
                .build();

        WebClient.UriSpec<WebClient.RequestBodySpec> request1 = client3.method(HttpMethod.POST);
        WebClient.UriSpec<WebClient.RequestBodySpec> request2 = client3.post();

        WebClient.RequestBodySpec uri1 = client3
                .method(HttpMethod.POST)
                .uri("/mock");

        WebClient.RequestBodySpec uri2 = client3
                .post()
                .uri("/mock");


        WebClient.RequestHeadersSpec requestSpec1 = WebClient
                .create()
                .method(HttpMethod.POST)
                .uri("/mock")
                .body(BodyInserters.fromPublisher(Mono.just("mockStuff"), String.class));


        BodyInserter<Publisher<String>, ReactiveHttpOutputMessage> inserter1 = BodyInserters
                .fromPublisher(Subscriber::onComplete, String.class);

        LinkedMultiValueMap map = new LinkedMultiValueMap();
        map.add("key1", "value1");
        map.add("key2", "value2");

        // BodyInserter<MultiValueMap<String, ?>, ClientHttpRequest> inserter2 = BodyInserters.fromMultipartData(map);
        BodyInserter<String, ReactiveHttpOutputMessage> inserter3 = BodyInserters.fromObject("body");

        WebClient.ResponseSpec response1 = uri1
                .body(inserter3)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now())
                .retrieve();

        String response2 = request1.uri("/mock")
                .exchange()
                .block()
                .bodyToMono(String.class)
                .block();
        String response3 = request2
                .uri("/mock")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return "test";
    }



}
