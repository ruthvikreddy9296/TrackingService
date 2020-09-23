/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tracking.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class AppMain implements WebMvcConfigurer{

    public static void main( String[] args )
    {
    	System.out.println( "Starting Tracking Service..." );
        SpringApplication.run(AppMain.class, args);
    }

}
