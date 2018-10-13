package com.example.gabriel.cryptocurrencies

import android.app.Activity
import android.app.Application
import com.example.gabriel.cryptocurrencies.di.DaggerAppComponent
import com.example.gabriel.cryptocurrencies.di.module.AppModule
import com.example.gabriel.cryptocurrencies.di.module.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class CryptocurrencyApp : Application(), HasActivityInjector {
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule(BuildConfig.BASE_URL))
            .build()
            .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }
}
