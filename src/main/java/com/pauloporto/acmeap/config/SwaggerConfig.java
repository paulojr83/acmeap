package com.pauloporto.acmeap.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${info.app.name}")
    private String title;

    @Value("${info.app.version}")
    private String version;
    @Value("${info.app.description}")
    private String description;
    @Value("${info.app.contact.name}")
    private String name;
    @Value("${info.app.contact.url}")
    private String url;
    @Value("${info.app.contact.email}")
    private String email;

    @Value("${info.app.terms-of-service-url}")
    private String terms;
    @Value("${info.app.license}")
    private String license;

    @Value("${info.app.license-url}")
    private String licenseUrl;

    private static final String REFERENCE ="Bearer +JWT";

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.pauloporto.acmeap"))
                .paths(PathSelectors.any()).build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {

        return new ApiInfo(
                title,
                description,
                version,
                terms,
                new Contact(null, url, null),
                license,
                licenseUrl, new ArrayList<>()
        );
    }
}