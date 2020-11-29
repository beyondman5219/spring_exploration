package com.beyond.man.spring_action;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootTest
class SpringActionApplicationTests {

    @Test
    void contextLoads() {
        ApplicationContext applicationContext = new AnnotationConfigReactiveWebApplicationContext();
        Object personDTO = applicationContext.getBean("personDTO");
        System.out.println("personDTO = " + personDTO);
    }

}
