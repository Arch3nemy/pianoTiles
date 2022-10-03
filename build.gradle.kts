buildscript {

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.2.1")
        //Actual kotlin version
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

