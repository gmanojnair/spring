package com.manoj.springsecurity.oauth.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.manoj.springsecurity.oauth")
public class HelloWorldConfiguration {
	

}