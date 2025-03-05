// Define the copyGitHooks task
tasks.register<Copy>("copyGitHooks") {
    description = "Copies the git hooks from /scripts to the .git/hooks folder."
    from("$rootDir/scripts/") {
        include("**/*.sh")
        rename { it.removeSuffix(".sh") }
    }
    into("$rootDir/.git/hooks")
}

// Define the installGitHooks task
tasks.register<Exec>("installGitHooks") {
    description = "Installs the pre-commit git hooks from the scripts directory."
    group = "git hooks"
    workingDir = rootDir
    commandLine("chmod", "-R", "+x", ".git/hooks/")
    dependsOn(tasks.named("copyGitHooks"))
    doLast {
        logger.info("Git hooks installed successfully.")
    }
}

// Configure task dependencies after evaluation
afterEvaluate {
    tasks.matching {
        it.name in listOf("preBuild", "build", "assembleDebug", "assembleRelease", "installDebug", "installRelease", "clean")
    }.configureEach {
        dependsOn(tasks.named("installGitHooks"))
    }
}
