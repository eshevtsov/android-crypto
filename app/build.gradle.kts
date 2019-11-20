plugins {
    id("com.android.application")
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

        applicationId = Android.applicationId
        versionCode = Android.versionCode
        versionName = Android.versionName

        vectorDrawables.useSupportLibrary = true
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
    api(project(Modules.featureCurrencyList))
    api(project(Modules.featureCurrencyDetail))
    api(project(Modules.featureCurrencyFilter))
    api(project(Modules.featureCurrencyConverter))

    implementation(Dependencies.kotlinStd)

    implementation(Dependencies.appcompat)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.material)
    implementation(Dependencies.constraintlayout)

    implementation(Dependencies.koinExt)
    implementation(Dependencies.koinScope)
    implementation(Dependencies.koinViewModel)

    implementation(Dependencies.coroutinesAndroid)
    implementation(Dependencies.navigation)
    implementation(Dependencies.navigationUi)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitMoshi)
    implementation(Dependencies.moshi)
    implementation(Dependencies.okhttp3)
    implementation(Dependencies.okhttp3Logging)

    implementation(Dependencies.roomKtx)
    implementation(Dependencies.roomRuntime)
    kapt(Compilers.roomCompiler)
}
