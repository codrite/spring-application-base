package com.app.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Arrays;

/**
 * Dispatcher Servlet Configuration
 */

@Configuration
@ImportResource("classpath:config/web-config.xml")
@ComponentScan(value = {"com.app.controller","com.app.web.config"})
public class WebConfig {

    @Value("${appname}")
    private String appname;

    @Bean
    public ContentNegotiatingViewResolver getContentNegotiatingViewResolver() {
        ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();
        View view = new MappingJackson2JsonView();
        contentNegotiatingViewResolver.setDefaultViews(Arrays.asList(view));
        return contentNegotiatingViewResolver;
    }

    public String getAppname() {
        return appname;
    }

}
