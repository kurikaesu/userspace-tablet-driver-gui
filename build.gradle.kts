import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.5.10"
    application
    id("org.openjfx.javafxplugin") version "0.0.10"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "me.kurikaesu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://oss.sonatype.org/content/repositories/snapshots")
    }
}

javafx {
    version = "17"
    modules("javafx.controls", "javafx.graphics")
}

application {
    mainClass.set("dev.villanueva.userland_utility.MainKt")
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.5.21")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.5.21")
    implementation("no.tornado:tornadofx:2.0.0-SNAPSHOT")
    implementation("com.github.kwhat:jnativehook:2.2-SNAPSHOT")
    implementation("com.kohlschutter.junixsocket:junixsocket-core:2.4.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.12.5")
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "16"
}

tasks.withType<ShadowJar> {
    archiveFileName.set("xp-pen-userland-utility.jar")
}
