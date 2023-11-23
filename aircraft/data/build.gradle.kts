plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.plugin.serialization")
}

dependencies {
    implementation(projects.aircraft.models)

    implementation(common.data)
    implementation(common.eventstore)
    implementation(common.eventstore.mongodbModels)
    implementation(common.mongodb)
    implementation(common.result)

    implementation(libraries.kodein.di)
    implementation(libraries.kotlinx.serialization.json)
}
