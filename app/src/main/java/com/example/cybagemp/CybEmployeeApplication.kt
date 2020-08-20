package com.example.cybagemp

import android.app.Application
import com.example.cybagemp.di.component.ApplicationComponent
import com.example.cybagemp.di.component.DaggerApplicationComponent
import com.example.cybagemp.di.module.ApplicationModule

class CybEmployeeApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

}