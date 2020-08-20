package com.example.cybagemp.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * data class for creating employee request
 */
data class CreateEmployeeRequest(
    @Expose
    @SerializedName("name")
    var name: String?,

    @Expose
    @SerializedName("salary")
    var salary: String?,

    @Expose
    @SerializedName("age")
    var age: String?
)