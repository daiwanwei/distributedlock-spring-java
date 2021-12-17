package com.example.distributedlockspringjava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Configuration
public class SwaggerConfig {
    @Bean
    public Docket customDocket(){
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
                .securitySchemes(security());
    }

    private ApiInfo getApiInfo(){
        return new ApiInfo(
                "rest Api Documentation",
                "This is REST API documentation of the Spring rest Api. If authentication is enabled, when calling the APIs use admin/admin",
                "1.0",
                "service",
                new Contact(
                        "wade",
                        "",
                        "alienwade007@gmail.com"
                ),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList()
        );

    }

    private List<SecurityScheme> security() {
        return Arrays.asList(new ApiKey("JWT", "Authorization", "header"));
    }
}
