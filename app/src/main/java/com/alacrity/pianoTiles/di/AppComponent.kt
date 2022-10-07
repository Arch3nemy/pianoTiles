package com.alacrity.pianoTiles.di

import com.alacrity.pianoTiles.App
import com.alacrity.pianoTiles.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, UseCaseModule::class, RepositoryModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(app: App)

}