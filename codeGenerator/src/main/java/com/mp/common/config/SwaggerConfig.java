package com.mp.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.xml.ws.Response;
import java.util.Date;

/**
 * @author lvlu
 * @date 2019-03-05 14:03
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket platformApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.zanclick"))
                .paths(PathSelectors.any())
                .build()
                .directModelSubstitute(Date.class,String.class)
                .genericModelSubstitutes(Response.class)
                .forCodeGeneration(true);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("API")
                .description("©2019 Copyright. Powered By ZanClick.Tech")
                .version("1.0").build();
    }
}
