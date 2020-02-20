package com.mohim.webclientexample.api;


import com.mohim.webclientexample.domain.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class MockController {

    @GetMapping(value = "/mock-get", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person testMockGet() {

        Person person = Person.builder()
                .name("Name")
                .age(22)
                .build();

        return person;

    }

    @PostMapping(value = "/mock-post", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person testMockPost(@RequestBody Person person) {
        return person;
    }

}
