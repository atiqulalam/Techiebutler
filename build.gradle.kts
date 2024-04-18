// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    //alias(libs.plugins.parcelable) apply false
}
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
        classpath(kotlin("gradle-plugin", version = "1.9.0"))
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.9.0") // Add this line
    }
}
