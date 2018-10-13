package com.example.gabriel.cryptocurrencies.di

import com.example.gabriel.cryptocurrencies.CryptocurrencyApp
import com.example.gabriel.cryptocurrencies.di.module.AppModule
import com.example.gabriel.cryptocurrencies.di.module.BuilderModule
import com.example.gabriel.cryptocurrencies.di.module.NetworkModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Component(modules = [AndroidInjectionModule::class, AppModule::class, BuilderModule::class, NetworkModule::class])
@Singleton
interface AppComponent {
    fun inject(app: CryptocurrencyApp)
}