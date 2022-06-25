package com.home.demos.samplebankmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan
@EnableWebMvc
public class SampleBankModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleBankModuleApplication.class, args);
    }

}
