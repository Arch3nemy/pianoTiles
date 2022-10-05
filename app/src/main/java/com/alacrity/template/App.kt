package com.alacrity.template

import android.app.Application
import com.alacrity.template.di.ApiModule
import com.alacrity.template.di.AppComponent
import com.alacrity.template.di.AppModule
import com.alacrity.template.di.DaggerAppComponent
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