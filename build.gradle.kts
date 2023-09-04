buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    `java-library`
    java
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "dev.denux"
val archivesBaseName = "jdaoauth2"
version = "2.0.0-alpha.1"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

publishing {
    publications {
        register("Release", MavenPublication::class) {
            from(components["java"])

            artifactId = archivesBaseName
            groupId = group as String
            version = version as String
        }
    }
}

repositories {
    mavenCentral()
    maven(url = "https://m2.dv8tion.net/releases")
    maven(url = "https://jitpack.io")
}

val lombokVersion = "1.18.28"

dependencies {
    api("org.apache.httpcomponents.client5:httpclient5:5.2.1")
    api("com.squareup.okhttp3:okhttp:4.11.0")
    api("org.slf4j:slf4j-api:2.0.9")
    api("com.google.code.gson:gson:2.10.1")

    //annotations
    compileOnly("com.google.code.findbugs:jsr305:3.0.2")

    // Lombok Annotations
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    testCompileOnly("org.projectlombok:lombok:$lombokVersion")
    testAnnotationProcessor("org.projectlombok:lombok:$lombokVersion")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}