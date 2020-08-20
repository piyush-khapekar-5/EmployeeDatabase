package com.example.cybagemp.di.component

import android.app.Application
import android.content.Context
import com.example.cybagemp.CybEmployeeApplication
import com.example.cybagemp.data.remote.NetworkService
import com.example.cybagemp.data.repository.EmployeeRepository
import com.example.cybagemp.di.ApplicationContext
import com.example.cybagemp.di.module.ApplicationModule
import com.example.cybagemp.utils.network.NetworkHelper
import com.example.cybagemp.utils.rx.SchedulerProvider
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: CybEmployeeApplication)

    fun getApplication(): Application

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getNetworkHelper(): NetworkHelper

    fun getSchedulerProvider(): SchedulerProvider

    fun getCompositeDisposable(): CompositeDisposable

    fun getEmployeeRepository() : EmployeeRepository

}