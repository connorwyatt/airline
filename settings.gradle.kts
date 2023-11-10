rootProject.name = "airline"

include(":services:aircraft")

include(":services:bookings")

include(":services:customers")

include(":services:flights")

pluginManagement {
    val spotlessVersion: String by settings

    repositories { gradlePluginPortal() }

    plugins { id("com.diffplug.spotless") version spotlessVersion }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage") repositories { mavenCentral() }

    @Suppress("UnstableApiUsage") repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
}
