package com.beyond.man;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AppTest {

    @Test
    public void shouldAnswerWithTrue() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring_javaBean.xml");
        Object person = applicationContext.getBean("person");
        System.out.println("person = " + person);
    }
}
