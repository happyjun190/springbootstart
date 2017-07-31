package com.ysb.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by wushenjun on 2017-02-06.
 */
@Configuration
@Conditional(AutoSwagger2Condition.class)
@EnableSwagger2
@EnableConfigurationProperties({Swagger2Properties.class})
public class Swagger2Config {
    @Autowired
    private Swagger2Properties properties;

    @Bean
    @ConditionalOnMissingBean
    public Docket createRestApi(){
        return  new Docket(DocumentationType.SWAGGER_2)
                .enable(properties.isEnable())
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(properties.getBasePackage()))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title(properties.getApiInfo().getTitle())
                .description(properties.getApiInfo().getDescription())
                //.termsOfServiceUrl("http://terms-of-services.url")
                .version(properties.getApiInfo().getVersion())
                .build();
    }
}
