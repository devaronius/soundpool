plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlinx.serialization)
}

group = "pl.ukaszapps.soundpool"
version = "1.0-SNAPSHOT"

rootProject.allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

android {
    namespace = "pl.ukaszapps.soundpool"
    compileSdk = 34

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain(17)
    }

    defaultConfig {
        minSdk = 26
    }

    buildFeatures {
        buildConfig = true
    }

    sourceSets {
        this.getByName("main") {
            //Adds the given source directory to this set.
            this.java.srcDir("src/main/kotlin")
        }
    }

    lint {
        disable += "InvalidPackage"
    }
}

dependencies {
    implementation("androidx.annotation:annotation:1.9.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
}
