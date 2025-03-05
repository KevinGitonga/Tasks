// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.spotless)
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.devtools.ksp) apply false
    alias(libs.plugins.kotlin.compose.compiler) apply false
}

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    spotless {
        kotlin {
            target("**/*.kt")
            targetExclude("$layout.getBuildDirectory()/**/*.kt")
            trimTrailingWhitespace()
            endWithNewline()
            // version, editorConfigPath, editorConfigOverride and customRuleSets are all optional
            ktlint("1.5.0")
                .setEditorConfigPath("$rootDir/.editorconfig") // sample unusual placement
            licenseHeaderFile(rootProject.file("spotless/copyright.txt"), "package|import|class|object|sealed|open|interface|abstract ")
        }
        kotlinGradle {
            target("*.gradle.kts")
            ktlint()
        }
        format("kts") {
            target("**/*.kts")
            @Suppress("DEPRECATION")
            targetExclude("$buildDir/**/*.kts")
        }
        format("misc") {
            target("**/*.md", "**/.gitignore")
            trimTrailingWhitespace()
            indentWithTabs()
            endWithNewline()
        }
    }
}
