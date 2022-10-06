package com.alacrity.pianoTiles.di

import com.alacrity.pianoTiles.use_cases.*
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface UseCaseModule {

    @Binds
    @Singleton
    fun bindGetTileScoresUseCaseImpl(impl: GetTileScoresUseCaseImpl): GetTileScoresUseCase

    @Binds
    @Singleton
    fun bindInsertTileScoreUseCaseImpl(impl: InsertTileScoreUseCaseImpl): InsertTileScoreUseCase

    @Binds
    @Singleton
    fun bindRemoveTileScoreUseCaseImpl(impl: RemoveTileScoreUseCaseImpl): RemoveTileScoreUseCase

}