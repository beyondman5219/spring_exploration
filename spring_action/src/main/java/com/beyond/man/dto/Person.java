package com.beyond.man.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
//@Component
public class Person {
    private String name;
    private Integer age;
}
