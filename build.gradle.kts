plugins {
    kotlin("jvm") version "1.9.23"
}

group = "rojojun"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.http4k:http4k-bom:4.48.0.0"))

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("io.strikt:strikt-core:0.34.0")
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-server-undertow")
    implementation("org.http4k:http4k-client-apache")
    implementation("org.http4k:http4k-server-jetty")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}