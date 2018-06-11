package com.ah8.app.ivservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class AH8DicomApplication extends SpringBootServletInitializer {
	
    public static void main(String[] args) {
        SpringApplication.run(AH8DicomApplication.class, args);
    }
    
    // Used when deploying to a standalone servlet container
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AH8DicomApplication.class);
    }
}
