package com.alacrity.pianoTiles.di

import android.content.Context
import android.content.res.Resources
import androidx.room.Room
import com.alacrity.pianoTiles.room.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context = context.applicationContext

    @Provides
    fun provideResources(): Resources = context.resources @Singleton


    @Provides
    fun provideDatabase(
        app: Context
    ) = Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "tileScores"
        ).build()

    @Singleton
    @Provides
    fun provideDao(db: AppDatabase) = db.playerScoreDao()




}