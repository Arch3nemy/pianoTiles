plugins {
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    core()
    di()
    async()
    retrofit()
}

fun DependencyHandlerScope.core() {
    implementation(Dependencies.other.kotlin)
}

fun DependencyHandlerScope.di() {
    api(Dependencies.di.javaxInject)
}

fun DependencyHandlerScope.async() {
    implementation(Dependencies.async.coroutinesCore)
}

fun DependencyHandlerScope.retrofit() {
    implementation(Dependencies.retrofit.retrofit)
    implementation(Dependencies.retrofit.gson)
}