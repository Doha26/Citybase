/*
 * MIT License
 *
 * Copyright (c) 2020 Foujeu pavel
 */

// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath(Dependencies.gradle)
        classpath(Dependencies.kotlinGradle)
        classpath(Dependencies.ktLint)
        classpath(Navigation.navigationPlugin)
        classpath(Navigation.safeArgs)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
