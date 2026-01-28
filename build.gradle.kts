plugins {
    id("java")
    id("xyz.wagyourtail.unimined") version ("1.4.1")
}

group = "org.example.illusion"
version = "0.1.0"

repositories {
    mavenCentral()
    maven("https://jitpack.io/")
}

unimined.minecraft {
    version("1.8.9")

    mappings {
        searge()
        mcp("stable", "22-1.8.9")
    }

    ornitheFabric {
        loader("0.18.4")
    }
}

dependencies {
    implementation("io.github.nevalackin:radbus:1.0.0")
    "include"("io.github.nevalackin:radbus:1.0.0")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8

    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

tasks {
    jar {
        manifest.attributes["Main-Class"] = "org.example.illusion.feature.container.Main"
    }
}