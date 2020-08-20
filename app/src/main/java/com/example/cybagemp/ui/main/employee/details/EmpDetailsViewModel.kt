package com.example.cybagemp.ui.main.employee.details

import androidx.lifecycle.MutableLiveData
import com.example.cybagemp.data.model.Employee
import com.example.cybagemp.data.repository.EmployeeRepository
import com.example.cybagemp.ui.base.BaseViewModel
import com.example.cybagemp.utils.common.Event
import com.example.cybagemp.utils.common.Resource
import com.example.cybagemp.utils.network.NetworkHelper
import com.example.cybagemp.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class EmpDetailsViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val allEmpList: ArrayList<Employee>,
    private val employeeRepository: EmployeeRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    // This can be needed for validation
    val empNameField: MutableLiveData<String> = MutableLiveData()
    val empAgeField: MutableLiveData<String> = MutableLiveData()
    val empSalaryField: MutableLiveData<String> = MutableLiveData()

    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val employeeDetails: MutableLiveData<Resource<Employee>> = MutableLiveData()
    val launchMain: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()

    companion object {
        const val TAG = "EmpDetailsViewModel"
    }

    override fun onCreate() {
    }

    fun onNameChange(email: String) = empNameField.postValue(email)

    fun onAgeChange(age: String) = empAgeField.postValue(age)

    fun onSalaryChange(salary: String) = empSalaryField.postValue(salary)

    /**
     * Update the employee details
     * @param empId: The employee id
     */
    fun updateEmployeeDetails(empId: String) {
        val name = empNameField.value
        val age = empAgeField.value
        val salary = empSalaryField.value
        val emp = Employee(empId, name, age, salary, "")
        if (networkHelper.isNetworkConnected()) {
            loading.postValue(true)
            compositeDisposable.addAll(
                employeeRepository.updateEmployeeDetails(emp)
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            loading.postValue(false)
                            employeeDetails.postValue(Resource.success(it))
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

    /**
     * Get the employee details
     * @param empId: The employee id
     */
    fun loadEmployeeDetails(empId : String) {
        if (networkHelper.isNetworkConnected()) {
            loading.postValue(true)
            compositeDisposable.addAll(
                employeeRepository.fetchEmployeeDetails(empId)
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            allEmpList.add(it)
                            loading.postValue(false)
                            employeeDetails.postValue(Resource.success(it))
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
     * Delete the employee base on ID
     * @param empId: employee ID
     */
    fun deleteEmployee(empId: String) {
        if (networkHelper.isNetworkConnected()) {
            loading.postValue(true)
            compositeDisposable.addAll(
                employeeRepository.deleteEmployeeDetails(empId)
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