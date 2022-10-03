plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdk = Dependencies.android.compileSdk
    buildToolsVersion = Dependencies.android.buildTools

    defaultConfig {
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":domain"))
    retrofit()
}

fun DependencyHandlerScope.retrofit() {
    implementation(Dependencies.retrofit.retrofit)
    implementation(Dependencies.retrofit.gson)
    implementation(Dependencies.retrofit.gsonConverter)
}