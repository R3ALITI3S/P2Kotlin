// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}

buildscript {
    /* ext {

        // compose_version = '1.0.0-beta09'
    } */

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        // Fail if standing - classpath("com.android.tools.build.gradle:7.0.0-beta05")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.20")
    }
}

