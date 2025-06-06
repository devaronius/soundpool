import java.util.Properties
import java.nio.file.Files
import java.nio.file.Path

val localProperties = Properties()
val localPropertiesFile : Path = rootProject.file("local.properties").toPath()
if (Files.exists(localPropertiesFile)) {

    Files.newBufferedReader(localPropertiesFile).use { reader ->
        localProperties.load(reader)
    }
}

val flutterRoot = localProperties.getProperty("flutter.sdk")
    ?: throw GradleException("Flutter SDK not found. Define location with flutter.sdk in the local.properties file.")

val flutterVersionCode: String = localProperties.getProperty("flutter.versionCode") ?: "1"
val flutterVersionName = localProperties.getProperty("flutter.versionName") ?: "1.0"

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("dev.flutter.flutter-gradle-plugin")
}

android {
    namespace = "com.example.demo"
    compileSdk = flutter.compileSdkVersion
    ndkVersion = "27.0.12077973"
    buildToolsVersion = "34.0.0"

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain(17)
    }

    defaultConfig {

        applicationId = "com.example.demo"
        minSdk = 26
        targetSdk = 34 //flutter.targetSdkVersion
        versionCode = flutterVersionCode.toInt()
        versionName = flutterVersionName
    }

    sourceSets {
        this.getByName("main") {
            //Adds the given source directory to this set.
            this.java.srcDir("src/main/kotlin")
        }
    }

    buildFeatures {
        buildConfig = true
    }

    lint {
        disable += "InvalidPackage"
    }

    buildTypes {
        getByName("release") {
            isShrinkResources = false
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
            isDebuggable = false
        }
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
            isDebuggable = true
        }
    }
}

flutter {
    source = "../.."
}
