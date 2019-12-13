plugins {
    application // https://docs.gradle.org/current/userguide/application_plugin.html
    id("com.github.johnrengelman.shadow") version "5.2.0" // https://plugins.gradle.org/plugin/com.github.johnrengelman.shadow
    id("org.jetbrains.kotlin.jvm") version "1.3.61"  // https://plugins.gradle.org/plugin/org.jetbrains.kotlin.jvm
}

// https://docs.gradle.org/current/dsl/org.gradle.api.Project.html#N152C6
application {
    mainClassName = "otpauth.MainKt"
}

// https://imperceptiblethoughts.com/shadow/configuration/
tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    minimize()
}

// https://kotlinlang.org/docs/reference/using-gradle.html
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

// https://docs.gradle.org/current/userguide/declaring_dependencies.html
dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.61") // https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-stdlib-jdk8
    implementation("com.google.zxing:core:3.4.0") // https://mvnrepository.com/artifact/com.google.zxing/core
    implementation("com.xenomachina:kotlin-argparser:2.0.7") // https://mvnrepository.com/artifact/com.xenomachina/kotlin-argparser

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:1.3.61") // https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-test-junit5
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.2") // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api
}

// https://docs.gradle.org/current/userguide/declaring_repositories.html
// https://mvnrepository.com/repos
repositories {
    maven { url = uri("https://maven.aliyun.com/repository/public") }
}
