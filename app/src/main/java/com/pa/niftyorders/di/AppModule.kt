package com.pa.niftyorders.di

import android.app.Application
import androidx.room.Room
import com.pa.niftyorders.data.local.NiftyDataBase
import com.pa.niftyorders.data.repository_impl.RepositoryImpl
import com.pa.niftyorders.domain.repository.Repository
import com.pa.niftyorders.domain.use_cases.CreateDemoData
import com.pa.niftyorders.domain.use_cases.GetProductsInCart
import com.pa.niftyorders.domain.use_cases.GetTopProducts
import com.pa.niftyorders.domain.use_cases.OrderUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNiftyDataBase(app: Application): NiftyDataBase {
        return Room.databaseBuilder(
            app,
            NiftyDataBase::class.java,
            NiftyDataBase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration() //TODO Migrations
            .build()
    }

    @Provides
    @Singleton
    fun provideOrderUseCases(repository: Repository): OrderUseCases{
        return OrderUseCases(
            getTopProducts = GetTopProducts(repository),
            getProductsInCart = GetProductsInCart(repository),
            createDemoData = CreateDemoData(repository)
        )
    }

}