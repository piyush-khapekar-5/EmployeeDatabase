package com.example.cybagemp.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * data class for employee details request
 */
data class EmployeeDetailsRequest(

    @Expose
    @SerializedName("id")
    var id: String,

    @Expose
    @SerializedName("employee_name")
    var employee_name: String?,

    @Expose
    @SerializedName("employee_salary")
    var employee_salary: String?,

    @Expose
    @SerializedName("employee_age")
    var employee_age: String?,

    @Expose
    @SerializedName("profile_image")
    var profile_image: String?
)