import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.tasks.bundling.Jar

val javalinVersion = "2.7.0"
val slf4jnVersion = "1.7.25"
val exposedVersion = "0.13.2"
val koinVersion = "2.0.0-rc-1"
val junitVersion = "5.4.1"
val kluentVersion = "1.49"
val postgresqlVersion = "42.2.5"
val jacksonVersion = "2.9.8"

plugins {
    kotlin("jvm") version "1.3.21"
    // packs application and dependencies
    id("com.github.johnrengelman.shadow") version "5.0.0"
}

group = "contacts"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("io.javalin:javalin:$javalinVersion")
    implementation("org.slf4j:slf4j-simple:$slf4jnVersion")
    implementation("org.jetbrains.exposed:exposed:$exposedVersion")
    implementation("org.koin:koin-core:$koinVersion")
    implementation("org.koin:koin-core-ext:$koinVersion")
    implementation("org.postgresql:postgresql:$postgresqlVersion")
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.amshove.kluent:kluent:$kluentVersion")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Jar> {
    archiveName = "contacts.jar"
    manifest {
        attributes("Main-Class" to "AppKt")
    }
}