package com.alacrity.template.di

import com.alacrity.template.use_cases.GetTileScoresUseCase
import com.alacrity.template.use_cases.GetTileScoresUseCaseImpl
import com.alacrity.template.use_cases.InsertTileScoreUseCase
import com.alacrity.template.use_cases.InsertTileScoreUseCaseImpl
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

}