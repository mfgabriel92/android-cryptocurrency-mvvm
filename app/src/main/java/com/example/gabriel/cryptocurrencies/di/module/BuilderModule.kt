package com.example.gabriel.cryptocurrencies.di.module

import com.example.gabriel.cryptocurrencies.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}