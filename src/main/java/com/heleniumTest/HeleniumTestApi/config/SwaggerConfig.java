package com.heleniumTest.HeleniumTestApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.function.Predicate;
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket docket(){
        Predicate<RequestHandler> basePackage = RequestHandlerSelectors
                .basePackage("com.heleniumTest.HeleniumTestApi.controller");
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(basePackage)
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Helenium Test API")
                .description("API for running Selenium tests with Helenium")
                .version("1.0")
                .build();
    }
}
