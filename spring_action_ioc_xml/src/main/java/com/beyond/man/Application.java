package com.beyond.man;

import com.beyond.man.config.CDPlayConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@Import(CDPlayConfig.class)
@ImportResource("classpath:SpringBean2.xml")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
