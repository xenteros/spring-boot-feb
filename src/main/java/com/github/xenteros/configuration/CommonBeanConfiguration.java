package com.github.xenteros.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonBeanConfiguration {

    @Bean
    public String helloWorld() {
        return "Hello World";
    }

}
