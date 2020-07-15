package com.nugux.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class Swagger {
    private var version: String? = null
    private var title: String? = null

    @Bean
    fun apiV1(): Docket {
        version = "1.0.0"
        title = "Nugux API $version"
        return Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
            .groupName(version)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.nugux.controller"))
            .paths(PathSelectors.ant("/api/v1/**"))
            .build()
            .apiInfo(apiInfo(title!!, version!!))
    }

    private fun apiInfo(title: String, version: String): ApiInfo {
        return ApiInfo(
            title,
            "Nugux API Docs",
            version,
            "www.example.com",
            Contact("Contact Me", "www.example.com", "foo@example.com"),
            "Licenses",
            "www.example.com",
            ArrayList()
        )
    }
}