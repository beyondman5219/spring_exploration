package com.beyond.man.controller;

import com.beyond.man.dto.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.time.Period;

@Controller
@ResponseBody
public class UserController {
    @Resource
    private Person person;

    @Value("${person.name}")
    private String name;

    @Value("${person.age}")
    private Integer age;

    @RequestMapping("/")
    String init() {
        person.setAge(age);
        person.setName(name);
        return person.getName() + person.getAge();
    }

}
