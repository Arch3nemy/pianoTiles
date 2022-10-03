package com.alacrity.template.di

import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context = context.applicationContext

    @Provides
    fun provideResources(): Resources = context.resources

}