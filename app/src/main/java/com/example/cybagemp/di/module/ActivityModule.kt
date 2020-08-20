package com.example.cybagemp.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cybagemp.data.repository.EmployeeRepository
import com.example.cybagemp.ui.base.BaseActivity
import com.example.cybagemp.ui.main.MainViewModel
import com.example.cybagemp.ui.main.employee.add.AddEmployeeViewModel
import com.example.cybagemp.ui.main.employee.EmpAdapter
import com.example.cybagemp.ui.main.employee.details.EmpDetailsViewModel
import com.example.cybagemp.utils.ViewModelProviderFactory
import com.example.cybagemp.utils.network.NetworkHelper
import com.example.cybagemp.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Basically it means that we can pass any class that extends BaseActivity which take
 * BaseViewModel subclass as parameter
 */
@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)

    @Provides
    fun provideMainViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        employeeRepository: EmployeeRepository
    ): MainViewModel =  ViewModelProviders.of(
        activity, ViewModelProviderFactory(MainViewModel::class) {
            MainViewModel(schedulerProvider, compositeDisposable, networkHelper, ArrayList(),
                employeeRepository)
        }).get(MainViewModel::class.java)

    @Provides
    fun provideEmpDetailsViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        employeeRepository: EmployeeRepository
    ): EmpDetailsViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(EmpDetailsViewModel::class) {
            EmpDetailsViewModel(
                schedulerProvider, compositeDisposable, networkHelper, ArrayList(),
                employeeRepository
            )
        }).get(EmpDetailsViewModel::class.java)

    @Provides
    fun provideAddEmployeeViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        employeeRepository: EmployeeRepository
    ): AddEmployeeViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(AddEmployeeViewModel::class) {
            AddEmployeeViewModel(
                schedulerProvider, compositeDisposable, networkHelper,
                employeeRepository
            )
        }).get(AddEmployeeViewModel::class.java)

    @Provides
    fun provideEmployeeAdapter() = EmpAdapter(activity.lifecycle, ArrayList())

}