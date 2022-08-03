buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    `java-library`
    java
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "dev.denux"
val archivesBaseName = "jdaoauth2"
version = "1.0.0-alpha.1"

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

dependencies {
    api("org.apache.httpcomponents.client5:httpclient5:5.1.3")
    api("com.squareup.okhttp3:okhttp:4.10.0")
    api("org.slf4j:slf4j-api:1.7.36")
    api("com.google.code.gson:gson:2.9.1")

    //annotations
    api("com.google.code.findbugs:jsr305:3.0.2")
    api("javax.annotation:javax.annotation-api:1.3.2")

    //compileOnly("net.dv8tion:JDA:5.0.0-alpha.17")

    testImplementation("com.github.Mokulu:discord-oauth2-api:1.0.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}