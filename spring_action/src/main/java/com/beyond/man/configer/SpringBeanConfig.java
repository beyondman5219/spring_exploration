package com.beyond.man.configer;

import com.beyond.man.dto.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanConfig {
    @Bean
    public Person getPerson() {
        return new Person();
    }
}
