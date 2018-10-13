package com.example.gabriel.cryptocurrencies.di.module

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.gabriel.cryptocurrencies.data.source.local.CryptocurrencyDatabase
import com.example.gabriel.cryptocurrencies.ui.main.viewmodel.CryptocurrencyViewModelFactory
import com.example.gabriel.cryptocurrencies.util.Util
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: Application) {
    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideDatabase(app: Application): CryptocurrencyDatabase = Room.databaseBuilder(
        app,
        CryptocurrencyDatabase::class.java,
        "cryptocurrencies.db"
    ).build()

    @Provides
    @Singleton
    fun provideCryptocurrencyDao(database: CryptocurrencyDatabase) = database.cryptocurrencyDao()

    @Provides
    @Singleton
    fun provideMainViewModel(factory: CryptocurrencyViewModelFactory): ViewModelProvider.Factory = factory

    @Provides
    @Singleton
    fun provideUtil(): Util = Util(app)
}