package com.app.web.config;

import com.app.controller.SimpleController;
import com.app.ws.GenericService;
import com.app.ws.GenericServiceInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.MediaType;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;
import org.springframework.remoting.jaxws.SimpleJaxWsServiceExporter;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;

import javax.xml.bind.JAXBException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Dispatcher Servlet Configuration
 */

@Configuration
@EnableWebMvc
@ImportResource("classpath:config/web-config.xml")
@ComponentScan(value = {"com.app.controller", "com.app.web.config", "com.app.web.ws"})
public class WebConfig extends WebMvcConfigurerAdapter {

    @Value("${appname}")
    private String appname;

    @Bean
    public ContentNegotiatingViewResolver getContentNegotiatingViewResolver() throws JAXBException {
        ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();

        List<View> supportedViews = new ArrayList<View>();
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setClassesToBeBound(SimpleController.InternalUseOnly.class);
        MarshallingView marshallingView = new MarshallingView(jaxb2Marshaller);
        supportedViews.add(marshallingView);
        supportedViews.add(new MappingJackson2JsonView());

        contentNegotiatingViewResolver.setDefaultViews(supportedViews);

        return contentNegotiatingViewResolver;
    }

    public String getAppname() {
        return appname;
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(true)
                .favorParameter(false)
                .ignoreAcceptHeader(true)
                .parameterName("mediaType")
                .useJaf(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("json", MediaType.APPLICATION_JSON);
    }

    @Bean
    public SimpleJaxWsServiceExporter getSimpleJaxWsServiceExporter() throws MalformedURLException {
        SimpleJaxWsServiceExporter simpleJaxWsServiceExporter = new SimpleJaxWsServiceExporter();
        simpleJaxWsServiceExporter.setBaseAddress("http://localhost:8080/Spring");
        return simpleJaxWsServiceExporter;
    }

}
