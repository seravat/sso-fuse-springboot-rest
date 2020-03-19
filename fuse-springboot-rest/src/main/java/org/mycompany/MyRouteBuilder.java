package org.mycompany;

import java.util.List;
import java.util.Vector;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.NoTypeConversionAvailableException;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MyRouteBuilder extends RouteBuilder {

    private static Logger logger = LoggerFactory.getLogger(MyRouteBuilder.class);

    @Override
    public void configure() throws Exception {

        restConfiguration()
        .component("servlet")
        .bindingMode(RestBindingMode.auto);
        
        rest("/hello")
        .get().route().bean(HelloBean.class , "sayHello");
        //("bean:HelloBean?method=sayHello");
    }
    
    @Bean(name = "helloWorld")
    public HelloBean helloBean() {
        return new HelloBean();
    }
}
