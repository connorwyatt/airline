plugins { id("org.jetbrains.kotlin.jvm") }

dependencies {
    implementation(libraries.hashids)

    testImplementation(testingLibraries.junit.jupiter)
    testImplementation(testingLibraries.strikt.core)

    testRuntimeOnly(testingLibraries.junit.jupiter.engine)
}
