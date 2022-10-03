package com.alacrity.template.di

import com.alacrity.template.use_cases.GetFactAboutNumberUseCase
import com.alacrity.template.use_cases.GetFactAboutNumberUseCaseImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface UseCaseModule {

    @Binds
    @Singleton
    fun bindNewMessageReceivedUseCase(impl: GetFactAboutNumberUseCaseImpl): GetFactAboutNumberUseCase

}