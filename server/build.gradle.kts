plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}

group = "com.sdui.dashboard"
version = "1.0.0"
application {
    mainClass.set("com.sdui.dashboard.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.content.negotiation.jvm)
    implementation(libs.ktor.serialization.kotlinx.json.jvm)
    implementation(libs.sqlite.jdbc)
    implementation(libs.bundles.exposed)
    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)
}