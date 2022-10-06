package com.alacrity.pianoTiles.di

import com.alacrity.pianoTiles.repository.Repository
import com.alacrity.pianoTiles.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindRepository(impl: RepositoryImpl): Repository

}