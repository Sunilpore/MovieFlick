// Top-level build file where you can add configuration options common to all sub-projects/modules.
/*buildscript {
    repositories {
        gradlePluginPortal()
    }

    dependencies {
        classpath("com.google.devtools.ksp:com,google.devtools.ksp.gradle.plugin:1.5.30-1.0.0")
    }

}*/

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.ksp) apply false
    alias (libs.plugins.hilt) apply false
}