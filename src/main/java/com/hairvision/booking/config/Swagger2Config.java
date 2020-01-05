package com.hairvision.booking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket api() {
        ParameterBuilder aparameterBuilder = new ParameterBuilder();
        aparameterBuilder.name("Hair-Vision")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .defaultValue("Hair Vision")
                .required(true)
                .build();
        java.util.List<Parameter> aParameters = new ArrayList<>();
        aParameters.add(aparameterBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(aParameters);
    }
}


