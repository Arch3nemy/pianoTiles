package com.alacrity.template.di

import com.alacrity.template.repository.Repository
import com.alacrity.template.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindRepository(impl: RepositoryImpl): Repository

}