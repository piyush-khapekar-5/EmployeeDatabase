package com.example.cybagemp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EmployeeAdd(
    @Expose
    @SerializedName("name")
    val name: String?,

    @Expose
    @SerializedName("salary")
    val salary: String?,

    @Expose
    @SerializedName("age")
    val age: String?

)