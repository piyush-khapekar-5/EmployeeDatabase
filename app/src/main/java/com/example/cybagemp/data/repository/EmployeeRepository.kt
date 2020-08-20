package com.example.cybagemp.data.repository

import com.example.cybagemp.data.model.Employee
import com.example.cybagemp.data.model.EmployeeAdd
import com.example.cybagemp.data.remote.NetworkService
import com.example.cybagemp.data.remote.request.CreateEmployeeRequest
import com.example.cybagemp.data.remote.request.EmployeeDetailsRequest
import com.example.cybagemp.data.remote.response.CreatedEmployeeResponse
import com.example.cybagemp.data.remote.response.EmployeeDeleteResponse
import io.reactivex.Single
import javax.inject.Inject

class EmployeeRepository @Inject constructor(
    private val networkService: NetworkService
) {

    fun fetchAllEmployeeList(): Single<List<Employee>> {
        return networkService.doHomeEmployeeListCall().map { it.data }
    }

    fun fetchEmployeeDetails(empId: String): Single<Employee> {
        return networkService.doEmployeeDetailsCall(empId).map { it.data }
    }

    fun updateEmployeeDetails(emp: Employee): Single<Employee> {
        return networkService.doUpdateEmployeeDetailsCall(
            emp.id,
            EmployeeDetailsRequest(
                emp.id, emp.employee_name, emp.employee_salary, emp.employee_age,
                emp.profile_image
            )
        ).map { it.data }
    }

    fun deleteEmployeeDetails(empId: String): Single<EmployeeDeleteResponse> {
        return networkService.doDeleteEmployeeCall(empId).map { it }
    }

    fun createEmployeeDetails(emp: EmployeeAdd): Single<CreatedEmployeeResponse> {
        return networkService.doCreateEmployeeCall(
            CreateEmployeeRequest(emp.name, emp.salary, emp.age)
        ).map { it }
    }
}