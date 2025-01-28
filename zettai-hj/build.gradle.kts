plugins {
    kotlin("jvm")
}

group = "com.rojojun"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.http4k:http4k-core:${property("http4kVersion")}")
    implementation("org.http4k:http4k-server-jetty:${property("http4kVersion")}")

    testImplementation(platform("org.junit:junit-bom:${property("junitBomVersion")}"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("org.http4k:http4k-client-jetty:${property("http4kVersion")}")
    testImplementation("com.ubertob.pesticide:pesticide-core:${property("pesticideVersion")}")
    testImplementation("io.strikt:strikt-core:${property("striktVersion")}")
}


tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = false // 테스트 출력에 println 표시 여부
    }

    // 테스트 캐시 무효화
    outputs.upToDateWhen { false }
}

kotlin {
    jvmToolchain(21)
}