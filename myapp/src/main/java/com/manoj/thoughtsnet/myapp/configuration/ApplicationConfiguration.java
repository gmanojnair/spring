package com.manoj.thoughtsnet.myapp.configuration;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.manoj.thoughtsnet.myapp.filter.CORSFilter;

@Configurable
public class ApplicationConfiguration {
	
	 @Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurerAdapter() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*");
	            }
	        };
	    }

	 
	 @Bean
	    public FilterRegistrationBean corsFilter() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration config = new CorsConfiguration();
	        config.setAllowCredentials(true);
	        config.addAllowedOrigin("*"); /// can be specific http url
	        config.addAllowedHeader("*");
	        config.addAllowedMethod("*");
	        config.addAllowedHeader("Content-Type");
	        //config.addAllowedHeader("x-xsrf-token");
	        //config.addAllowedHeader("Authorization");
	        //Required for Chrome
	        config.addAllowedHeader("Access-Control-Allow-Headers");
	        //config.addAllowedHeader("Origin");
	        //config.addAllowedHeader("Accept");
	        config.addAllowedHeader("X-Requested-With");
	        config.addAllowedHeader("Access-Control-Request-Method");
	        config.addAllowedHeader("Access-Control-Request-Headers");
	        source.registerCorsConfiguration("/**", config);
	       
	        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
	        bean.setOrder(0);
	        return bean;
	    }
	 
	 @Bean
	    public FilterRegistrationBean customFilter() {
		 FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
		    filterRegBean.setFilter(new CORSFilter());
		    List<String> urlPatterns = new ArrayList<String>();
		    urlPatterns.add("*");
		    filterRegBean.setUrlPatterns(urlPatterns);
		    filterRegBean.setOrder(1);
		    return filterRegBean;
	    }
	 
	
}
