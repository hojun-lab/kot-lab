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

    implementation("io.ktor:ktor-client-jetty:1.6.8")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("io.strikt:strikt-core:0.34.0")
    testImplementation("org.http4k:http4k-client-jetty")
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-server-undertow")
    implementation("org.http4k:http4k-client-apache")
    implementation("org.http4k:http4k-server-jetty")
    implementation("org.http4k:http4k-client-jetty")

    // Jetty 필수 의존성
    implementation("org.eclipse.jetty:jetty-util:11.0.15")
    implementation("org.eclipse.jetty.http2:http2-hpack:11.0.15")
    implementation("org.eclipse.jetty:jetty-server:11.0.15")
    implementation("org.eclipse.jetty:jetty-servlet:11.0.15")

    // SLF4J 로깅 구현체
    implementation("org.slf4j:slf4j-simple:2.0.9")

    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("com.ubertob.pesticide:pesticide-core:1.6.6")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}