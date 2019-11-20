plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(Android.compileSdkVersion)
    buildToolsVersion(Android.buildToolsVersion)

    defaultConfig {
        minSdkVersion(Android.minSdkVersion)
        targetSdkVersion(Android.targetSdkVersion)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        setSourceCompatibility(1.8)
        setTargetCompatibility(1.8)
    }

    dataBinding.isEnabled = true
    lintOptions.isAbortOnError = true
}

dependencies {
    api(project(Modules.coreDesign))

    implementation(Dependencies.picasso)

    implementation(Dependencies.coroutinesAndroid)
    implementation(Dependencies.lifecycle)
    implementation(Dependencies.lifecycleCommon)
    implementation(Dependencies.lifecycleRuntimeKtx)
    implementation(Dependencies.livedataKtx)
}