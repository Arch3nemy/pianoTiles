package com.alacrity.template.di

import com.alacrity.template.App
import com.alacrity.template.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class, UseCaseModule::class, RepositoryModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(app: App)

}