package com.mohim.webclientexample.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Person {
    private String name;
    private int age;
}
