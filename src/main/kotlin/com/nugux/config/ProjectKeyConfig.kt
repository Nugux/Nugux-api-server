package com.nugux.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.FileInputStream
import java.util.*

@Configuration
class ProjectKeyConfig {
    @Bean
    fun projectKey(): String {
        val properties = Properties()
        val propertiesFile = System.getProperty("user.dir") + "\\key.properties";
        val inputStream = FileInputStream(propertiesFile)
        properties.load(inputStream)

        return properties["ProjectKey"] as String
    }
}