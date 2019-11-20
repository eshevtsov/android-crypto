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
    api(project(Modules.coreFeature))
    api(project(Modules.data))

    implementation(Dependencies.kotlinStd)

    implementation(Dependencies.appcompat)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.material)
    implementation(Dependencies.constraintlayout)

    implementation(Dependencies.coroutinesAndroid)
    implementation(Dependencies.lifecycle)
    implementation(Dependencies.lifecycleCommon)
    implementation(Dependencies.lifecycleRuntimeKtx)
    implementation(Dependencies.livedataKtx)
    implementation(Dependencies.viewmodelKtx)
}