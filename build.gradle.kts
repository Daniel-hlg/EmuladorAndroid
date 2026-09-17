// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.android) apply false
}

subprojects {
    configurations.all {
        resolutionStrategy.eachDependency {
            if (requested.group == "androidx.test.espresso") {
                useVersion("3.6.1")
            }
            if (requested.group == "androidx.test" && (requested.name == "monitor" || requested.name == "runner" || requested.name == "core" || requested.name == "rules")) {
                if (requested.name == "monitor") useVersion("1.7.2")
                if (requested.name == "runner") useVersion("1.6.2")
                if (requested.name == "core") useVersion("1.6.1")
                if (requested.name == "rules") useVersion("1.6.1")
            }
        }
    }
}