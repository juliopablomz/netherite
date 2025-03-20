plugins {
    id("com.gradleup.shadow") version "8.3.0"
    id("java")
}

group = "project.netherite"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.minestom:minestom-snapshots:39d445482f")
    implementation("ch.qos.logback:logback-classic:1.5.18")

    implementation("org.jetbrains:annotations:26.0.2")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

java {
    toolchain {
        // Minestom has a minimum Java version of 21.
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks {
    test {
        useJUnitPlatform()
    }

    jar {
        manifest {
            attributes["Main-Class"] = "project.netherite.HelloWorld"
        }
    }

    build {
        dependsOn(shadowJar)
    }

    shadowJar {
        mergeServiceFiles()

        // Prevent the -all suffix on the shadow jar file.
        archiveClassifier.set("")
    }
}