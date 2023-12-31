rootProject.name = "airline"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

val commonGroupId = "com.github.connorwyatt.common-kotlin"

val useLocalCommonPackages: Boolean = false

val commonPackages =
    listOf(
        Triple("configuration", "configuration", ":configuration"),
        Triple("data", "data", ":data"),
        Triple("eventstore", "eventstore", ":eventstore"),
        Triple(
            "eventstore-mongodbModels",
            "eventstore-mongodb-models",
            ":eventstore:mongodb-models"
        ),
        Triple("http", "http", ":http"),
        Triple("mongodb", "mongodb", ":mongodb"),
        Triple("optional", "optional", ":optional"),
        Triple("rabbitmq", "rabbitmq", ":rabbitmq"),
        Triple("result", "result", ":result"),
        Triple("server", "server", ":server"),
        Triple("time", "time", ":time"),
        Triple("validation", "validation", ":validation"),
    )

if (useLocalCommonPackages) {
    includeBuild("../common") {
        dependencySubstitution {
            commonPackages.forEach { (_, artifact, project) ->
                substitute(module("$commonGroupId:$artifact")).using(project(":$project"))
            }
        }
    }
}

include(":aircraft:commands")

include(":aircraft:data")

include(":aircraft:events")

include(":aircraft:models")

include(":aircraft:service")

include(":shared:ids")

pluginManagement {
    val kotlinVersion: String by settings
    val ktorVersion: String by settings
    val spotlessVersion: String by settings

    repositories { gradlePluginPortal() }

    plugins {
        id("com.diffplug.spotless") version spotlessVersion
        id("org.jetbrains.kotlin.jvm") version kotlinVersion
        id("org.jetbrains.kotlin.plugin.serialization") version kotlinVersion
        id("io.ktor.plugin") version ktorVersion
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }

    @Suppress("UnstableApiUsage") repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    versionCatalogs {
        val commonVersion: String by settings
        val hashidsVersion: String by settings
        val jUnitVersion: String by settings
        val kodeinVersion: String by settings
        val kotlinxSerializationVersion: String by settings
        val ktorVersion: String by settings
        val logbackVersion: String by settings
        val logstashLogbackEncoderVersion: String by settings
        val mongoDBDriverVersion: String by settings
        val striktVersion: String by settings

        create("common") {
            commonPackages.forEach { (alias, artifact, _) ->
                library(alias, commonGroupId, artifact).version(commonVersion)
            }
        }

        create("libraries") {
            library("hashids", "org.hashids", "hashids").version(hashidsVersion)
            library("kodein-di", "org.kodein.di", "kodein-di").version(kodeinVersion)
            library(
                    "kodein-di-framework-ktor-server",
                    "org.kodein.di",
                    "kodein-di-framework-ktor-server-jvm"
                )
                .version(kodeinVersion)
            library(
                    "kotlinx-serialization-json",
                    "org.jetbrains.kotlinx",
                    "kotlinx-serialization-json"
                )
                .version(kotlinxSerializationVersion)
            library("ktor-server-core", "io.ktor", "ktor-server-core").version(ktorVersion)
            library("logback-classic", "ch.qos.logback", "logback-classic").version(logbackVersion)
            library("logstash-logbackEncoder", "net.logstash.logback", "logstash-logback-encoder")
                .version(logstashLogbackEncoderVersion)
            library("mongoDB-driver", "org.mongodb", "mongodb-driver-kotlin-coroutine")
                .version(mongoDBDriverVersion)
        }

        create("testingLibraries") {
            library("junit-jupiter", "org.junit.jupiter", "junit-jupiter").version(jUnitVersion)
            library("junit-jupiter-engine", "org.junit.jupiter", "junit-jupiter-engine")
                .version(jUnitVersion)
            library("strikt.core", "io.strikt", "strikt-core").version(striktVersion)
        }
    }
}
