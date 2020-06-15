package com.yuan.oa_web.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 启动swagger
 * configuration注解标记配置类
 */
@Configuration
@EnableSwagger2
public class SwaggerApp{
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yuan.oa_web.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    //构建api文档的详细信息函数，注意这里的注解引用的是哪个
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                //页面标题
                .title("springboot使用swagger2构建API")
                //创建人
                .contact(new Contact("yuan","www.baidu.com",""))
                //版本号
                .version("1.0")
                //描述
                .description("描述")
                .build();
    }
}
