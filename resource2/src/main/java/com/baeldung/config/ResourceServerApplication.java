package com.baeldung.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
public class ResourceServerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
   	 System.setProperty("http.proxyHost", "127.0.0.1");
		System.setProperty("https.proxyHost", "127.0.0.1");
		System.setProperty("http.proxyPort", "8888");
		System.setProperty("https.proxyPort", "8888");
        SpringApplication.run(ResourceServerApplication.class, args);
    }
    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
    
}