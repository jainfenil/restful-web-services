package com.springbootVersionOne.rest.webservices.restfulwebservices;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public static Contact DEFAULT_CONTACT = new Contact(
            "Fenil Jain", "http://spring@yahoo.com", "spring@yahoo.com");
    public static ApiInfo DEFAULT_API_INFO = new ApiInfo("Awesome API Title",
            "Awesome API Title Documentation","1.0",
            "urn:tos",DEFAULT_CONTACT,
            "Apache 2.0","http://www.apache.org/licenses/LICENSE-2.0");
    private static final Set<String> DEFAULT_PRODUCER_AND_CONSUMES=
            new HashSet<String>(Arrays.asList("application/json","application/xml"));

    /*
        link to see documentation in json format
        http://localhost:8080/v2/api-docs
        http://localhost:8080/swagger-ui.html //for swagger ui
        *///Bean - Docket
    //Swagger 2
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCER_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCER_AND_CONSUMES);
    }
}
