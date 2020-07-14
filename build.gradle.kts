plugins {
    kotlin("jvm") version "1.3.61"
}

group = "com.nugux"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

extra.apply {
    set("kotlinGradlePluginVersion", "1.3.61")
    set("gsonVersion", "2.8.5")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${extra["kotlinGradlePluginVersion"]}")

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