plugins {
    id("org.springframework.boot") version "2.2.0.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.3.72"
    id("org.jetbrains.kotlin.plugin.spring") version "1.3.72"
    id("org.jetbrains.kotlin.plugin.noarg") version "1.3.72"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.3.72"
    kotlin("jvm") version "1.3.61"
}

group = "com.nugux"
version = "1.0.0"

repositories {
    mavenCentral()
    mavenLocal()
    jcenter()
}

extra.apply {
    set("kotlinGradlePluginVersion", "1.3.61")
    set("postgresqlVersion", "42.2.14")
    set("hibernateVersion", "5.4.1.Final")
    set("swaggerVersion", "2.9.2")
    set("jtsVersion", "1.13")
    set("gsonVersion", "2.8.5")
    set("kotlinCsvVersion", "0.10.4")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${extra["kotlinGradlePluginVersion"]}")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.postgresql:postgresql:${extra["postgresqlVersion"]}")
    implementation("org.hibernate:hibernate-spatial:${extra["hibernateVersion"]}")
    implementation("org.hibernate:hibernate-spatial:${extra["hibernateVersion"]}")
    implementation("org.hibernate:hibernate-core:${extra["hibernateVersion"]}")
    implementation("io.springfox:springfox-swagger2:${extra["swaggerVersion"]}")
    implementation("io.springfox:springfox-swagger-ui:${extra["swaggerVersion"]}")
    implementation("com.vividsolutions:jts:${extra["jtsVersion"]}")
    implementation("com.google.code.gson:gson:${extra["gsonVersion"]}")

    implementation("com.github.doyaaaaaken:kotlin-csv-jvm:${extra["kotlinCsvVersion"]}")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.3.50")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}