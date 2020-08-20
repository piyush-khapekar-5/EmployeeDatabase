package com.example.cybagemp.di.component

import com.example.cybagemp.di.module.ActivityModule
import com.example.cybagemp.ui.main.MainActivity
import com.example.cybagemp.ui.main.employee.add.AddEmployeeActivity
import com.example.cybagemp.ui.main.employee.details.EmpDetailsActivity
import com.mindorks.bootcamp.instagram.di.ActivityScope
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(activity: MainActivity)

    fun inject(activity: EmpDetailsActivity)

    fun inject(activity: AddEmployeeActivity) {
    }
}