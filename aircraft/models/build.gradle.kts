plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.plugin.serialization")
}

dependencies {
    implementation(projects.shared.ids)

    implementation(common.optional)
    implementation(common.result)

    implementation(libraries.kotlinx.serialization.json)

    testImplementation(testingLibraries.junit.jupiter)
    testImplementation(testingLibraries.strikt.core)

    testRuntimeOnly(testingLibraries.junit.jupiter.engine)
}
