package com.example.cybagemp.ui.main.employee.add

import androidx.lifecycle.MutableLiveData
import com.example.cybagemp.data.model.CreateEmployee
import com.example.cybagemp.data.model.Employee
import com.example.cybagemp.data.model.EmployeeAdd
import com.example.cybagemp.data.repository.EmployeeRepository
import com.example.cybagemp.ui.base.BaseViewModel
import com.example.cybagemp.utils.common.Event
import com.example.cybagemp.utils.common.Resource
import com.example.cybagemp.utils.network.NetworkHelper
import com.example.cybagemp.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable


class AddEmployeeViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val employeeRepository: EmployeeRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    override fun onCreate() {}

    // can be used for validations
    val empNameField: MutableLiveData<String> = MutableLiveData()
    val empAgeField: MutableLiveData<String> = MutableLiveData()
    val empSalaryField: MutableLiveData<String> = MutableLiveData()

    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val launchMain: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()

    companion object {
        const val TAG = "AddEmployeeViewModel"
    }

    fun onNameChange(email: String) = empNameField.postValue(email)

    fun onAgeChange(age: String) = empAgeField.postValue(age)

    fun onSalaryChange(salary: String) = empSalaryField.postValue(salary)

    /**
     * Update the employee details
     * @param empId: The employee id
     */
    fun createEmployee() {
        val name = empNameField.value
        val age = empAgeField.value
        val salary = empSalaryField.value
        val emp = EmployeeAdd(name, age, salary)
        if (networkHelper.isNetworkConnected()) {
            loading.postValue(true)
            compositeDisposable.addAll(
                employeeRepository.createEmployeeDetails(emp)
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            loading.postValue(false)
                            launchMain.postValue(Event(emptyMap()))
                        },
                        {
                            loading.postValue(false)
                            handleNetworkError(it)
                        }
                    )
            )
        }
    }
}