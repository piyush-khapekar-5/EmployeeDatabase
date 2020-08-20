package com.example.cybagemp.data.remote.response

import com.example.cybagemp.data.model.Employee
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EmployeeDetailsResponse(
    @Expose
    @SerializedName("status")
    var status: String,

    @Expose
    @SerializedName("data")
    val data: Employee,

    @Expose
    @SerializedName("message")
    val message: String


)