package com.nalbam.bot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //@Value("${spring.application.name}")
    private final String name = "bot";

    private final String base = "com.nalbam";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(this.base))
                .paths(PathSelectors.any())
                .build()
                //.ignoredParameterTypes(Pageable.class)
                .apiInfo(new ApiInfo(this.name, "", "", "", ApiInfo.DEFAULT_CONTACT, "", "", new ArrayList<>()));
    }

}
