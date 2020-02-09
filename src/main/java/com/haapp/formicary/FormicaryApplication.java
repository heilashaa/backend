package com.haapp.formicary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
/*todo @EnableConfigurationProperties*/
public class FormicaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(FormicaryApplication.class, args);
    }

}
