package controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyWebConfig {

    @Bean
    public HelloController helloController() {
        return new HelloController();
    }
}
