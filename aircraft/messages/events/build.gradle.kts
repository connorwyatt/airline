plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.plugin.serialization")
}

dependencies {
    implementation(projects.aircraft.messages.models)

    implementation(common.eventstore)

    implementation(libraries.kodein.di)
    implementation(libraries.kotlinx.serialization.json)
}
