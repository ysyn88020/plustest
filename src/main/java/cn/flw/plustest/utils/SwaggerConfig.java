package cn.flw.plustest.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket buildDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                .select()
                //要扫描的API(Controller)基础包
                .apis(RequestHandlerSelectors.basePackage("cn.flw.plustest.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo buildApiInfo() {

        return new ApiInfoBuilder()
                .title("用户信息API文档")
                .description("这里除了查看接口功能外，还提供了调试测试功能")
                .contact("f_liwen")
                .version("1.0")
                .build();

    }
}
