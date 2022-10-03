@file:Suppress("unused")

object Other {

    /**
     * [Timber](https://mvnrepository.com/artifact/com.jakewharton.timber/timber)
     * No-nonsense injectable logging.
     */
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    /**
     * [Core Kotlin Extensions](https://developer.android.com/kotlin/ktx#core)
     * Kotlin extensions for 'core' artifact
     */
    const val ktxCore = "androidx.core:core-ktx:${Versions.ktxCore}"

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"

    const val material = "com.google.android.material:material:${Versions.material}"

    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"

}