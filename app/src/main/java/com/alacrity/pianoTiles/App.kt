package com.alacrity.pianoTiles

import android.app.Application
import com.alacrity.pianoTiles.di.ApiModule
import com.alacrity.pianoTiles.di.AppComponent
import com.alacrity.pianoTiles.di.AppModule
import com.alacrity.pianoTiles.di.DaggerAppComponent
import timber.log.Timber

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        appComponent = DaggerAppComponent
            .builder()
            .apiModule(ApiModule("http://numbersapi.com/"))
            .appModule(AppModule(this))
            .build()
            .apply { inject(this@App) }
    }

    companion object {

        lateinit var appComponent: AppComponent

    }
}