buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Plugins.kotlin)
        classpath(Plugins.gradle)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

task<Delete>("clean") { delete(buildDir) }