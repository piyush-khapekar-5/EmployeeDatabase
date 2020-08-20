package com.example.cybagemp.ui.main

import androidx.lifecycle.MutableLiveData
import com.example.cybagemp.data.model.Employee
import com.example.cybagemp.data.repository.EmployeeRepository
import com.example.cybagemp.ui.base.BaseViewModel
import com.example.cybagemp.utils.common.Event
import com.example.cybagemp.utils.common.Resource
import com.example.cybagemp.utils.network.NetworkHelper
import com.example.cybagemp.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val allEmpList: ArrayList<Employee>,
    private val employeeRepository: EmployeeRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper){

    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val employees: MutableLiveData<Resource<List<Employee>>> = MutableLiveData()
    val addEmpNavigation = MutableLiveData<Event<Boolean>>()
    companion object {
        const val TAG = "MainViewModel"
    }

    override fun onCreate() {
        loadEmpList()
    }

    /**
     * Fetch the employee list
     */
    private fun loadEmpList() {
        if (networkHelper.isNetworkConnected()) {
            loading.postValue(true)
            compositeDisposable.addAll(
                employeeRepository.fetchAllEmployeeList()
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            allEmpList.addAll(it)
                            loading.postValue(false)
                            employees.postValue(Resource.success(it))
                        },
                        {
                            loading.postValue(false)
                            handleNetworkError(it)
                        }
                    )
            )
        }
    }

    /**
     * Notifies when Add Employee is selected
     */
    fun onAddEmpSelected() {
        addEmpNavigation.postValue(Event(true))
    }
}