package com.slemma.test.config;

import com.slemma.test.repo.*;
import com.slemma.test.service.HttpService;
import com.slemma.test.service.HttpServiceImpl;
import com.slemma.test.service.ParsingService;
import com.slemma.test.service.ParsingServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;

/**
 * Created by turov on 10.05.2017.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.slemma.test.controller"})
public class AppConfig {
    @Bean
    public ResourceUrlEncodingFilter resourceUrlEncodingFilter() {
        return new ResourceUrlEncodingFilter();
    }


    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
        resource.setBasename("classpath:messages");
        resource.setDefaultEncoding("UTF-8");
        return resource;
    }


    @Bean(name = "httpService")
    public HttpService httpService(){
        return new HttpServiceImpl();
    }

    @Bean(name = "albumRepo")
    public AlbumRepo albumRepo(){
        return new AlbumRepoImpl();
    }

    @Bean(name = "postRepo")
    public PostRepo postRepo(){
        return new PostRepoImpl();
    }

    @Bean(name = "todosRepo")
    public TodosRepo todosRepo(){
        return new TodosRepoImpl();
    }

    @Bean(name = "parsingService")
    public ParsingService parsingService(){
        return new ParsingServiceImpl();
    }


}
