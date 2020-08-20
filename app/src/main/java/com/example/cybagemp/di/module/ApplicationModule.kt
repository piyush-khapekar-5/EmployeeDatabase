package com.example.cybagemp.di.module

import android.app.Application
import android.content.Context
import com.example.cybagemp.BuildConfig

import com.example.cybagemp.CybEmployeeApplication
import com.example.cybagemp.data.remote.NetworkService
import com.example.cybagemp.data.remote.Networking
import com.example.cybagemp.di.ApplicationContext
import com.example.cybagemp.utils.network.NetworkHelper
import com.example.cybagemp.utils.rx.RxSchedulerProvider
import com.example.cybagemp.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: CybEmployeeApplication) {
    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = application

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()

    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =
        Networking.create(
            BuildConfig.API_KEY,
            BuildConfig.BASE_URL,
            application.cacheDir,
            10 * 1024 * 1024 // 10MB
        )

    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)
}