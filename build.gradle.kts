plugins { id("com.diffplug.spotless") }

tasks {
    create("installLocalGitHook") {
        delete { delete(File(rootDir, ".git/hooks/pre-commit")) }
        copy {
            from(File(rootDir, "scripts/pre-commit"))
            into(File(rootDir, ".git/hooks"))
            fileMode = 0b111101101
        }
    }

    build { dependsOn("installLocalGitHook") }
}

allprojects {
    apply(plugin = "com.diffplug.spotless")

    spotless {
        kotlin {
            target("**/*.kt", "**/*.kts")
            ktfmt(project.properties["ktfmtVersion"] as String).kotlinlangStyle()
        }
    }
}
