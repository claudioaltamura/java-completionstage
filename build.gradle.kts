import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    java
    eclipse
    idea
    id("com.diffplug.spotless") version "5.15.0"
    id("com.github.ben-manes.versions") version "0.39.0"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.assertj:assertj-core:3.20.2")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.2")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.2")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

spotless {
    java {
        googleJavaFormat()
    }
    kotlinGradle {
        ktlint()
    }
}

tasks {
    test {
        useJUnitPlatform()
        testLogging {
            events = setOf(TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.FAILED)
        }
    }
}
