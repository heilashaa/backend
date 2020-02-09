package com.haapp.formicary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@PropertySource("classpath:application.yaml")
public class SwaggerConfig {

    private final Environment environment;

    public SwaggerConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public Docket apiDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.haapp.formicary"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(createApiInfo())
                /*.globalOperationParameters(
                        Arrays.asList(new ParameterBuilder()
                                .name(X_LOCALE)
                                .description("Locale header")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(true)
                                .build()))*/
                /*.securitySchemes(createSecuritySchemes())*/;
    }

    private ApiInfo createApiInfo() {
        return new ApiInfoBuilder()
                .title(environment.getProperty("swagger.api-info.title"))
                .description(environment.getProperty("swagger.api-info.description"))
                .version(environment.getProperty("swagger.api-info.version"))
                .license(environment.getProperty("swagger.api-info.license"))
                .licenseUrl(environment.getProperty("swagger.api-info.licenseUrl"))
                .contact(new Contact(
                        environment.getProperty("swagger.api-info.contact.name"),
                        environment.getProperty("swagger.api-info.contact.url"),
                        environment.getProperty("swagger.api-info.contact.email")
                ))
                .build();
    }

//    private List<SecurityScheme> createSecuritySchemes() {
//        return singletonList(new BasicAuth(BASIC_AUTH));
//    }
}
