plugins {
    id("org.springframework.boot") version "2.2.0.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
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
    set("gsonVersion", "2.8.5")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${extra["kotlinGradlePluginVersion"]}")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.postgresql:postgresql:${extra["postgresqlVersion"]}")
    implementation("com.google.code.gson:gson:${extra["gsonVersion"]}")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}