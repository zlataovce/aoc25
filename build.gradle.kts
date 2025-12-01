import java.io.InputStream
import java.util.Locale

plugins {
    kotlin("jvm") version "2.2.20"
}

group = "me.kcra"
version = "1.0.0-SNAPSHOT"
description = "my solutions to Advent of Code 2025"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)
}

// adds tasks for running individual solutions under the "aoc" group
// inputs are defined in <solution file name>_input.txt files adjacent to the solution source file
sourceSets.main.configure {
    kotlin.files.forEach { file ->
        if (file.extension != "kt") return@forEach

        val name = file.nameWithoutExtension
        val capitalName = name.replaceFirstChar { it.titlecase(Locale.ENGLISH) }

        tasks.register<JavaExec>("run$capitalName") {
            group = "aoc"
            mainClass = "${capitalName}Kt"
            classpath = runtimeClasspath

            val inputFile = file.resolveSibling("${name}_input.txt")
            if (inputFile.isFile) {
                standardInput = inputFile.inputStream()
            }
        }
    }
}
