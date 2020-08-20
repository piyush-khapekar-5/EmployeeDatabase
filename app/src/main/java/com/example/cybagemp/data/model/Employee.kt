package com.example.cybagemp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * data class for Employee
 */
data class Employee(
    @Expose
    @SerializedName("id")
    val id: String,

    @Expose
    @SerializedName("employee_name")
    val employee_name: String?,

    @Expose
    @SerializedName("employee_age")
    val employee_age: String?,

    @Expose
    @SerializedName("employee_salary")
    val employee_salary: String?,

    @Expose
    @SerializedName("profile_image")
    val profile_image: String
)