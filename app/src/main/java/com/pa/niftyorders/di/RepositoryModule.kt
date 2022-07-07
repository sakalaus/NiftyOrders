package com.pa.niftyorders.di

import com.pa.niftyorders.data.repository_impl.RepositoryImpl
import com.pa.niftyorders.data.repository_mock.RepositoryMock
import com.pa.niftyorders.domain.repository.Repository
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
    abstract fun bindRepository(
        repository: RepositoryImpl
    ): Repository
}