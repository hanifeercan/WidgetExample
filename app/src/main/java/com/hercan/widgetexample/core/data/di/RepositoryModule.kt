package com.hercan.widgetexample.core.data.di

import com.hercan.widgetexample.core.data.repo.Repository
import com.hercan.widgetexample.core.data.repo.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindBookRepository(bookRepositoryImpl: RepositoryImpl): Repository
}