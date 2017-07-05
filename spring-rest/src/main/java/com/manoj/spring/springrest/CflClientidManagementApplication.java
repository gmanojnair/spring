package com.manoj.spring.springrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class CflClientidManagementApplication extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(CflClientidManagementApplication.class);

    public static void main(String[] args) {
        log.info("ClientId Management Application Started");
        SpringApplication.run(CflClientidManagementApplication.class, args);
        log.info("ClientId Management Application End");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CflClientidManagementApplication.class);
    }

}
