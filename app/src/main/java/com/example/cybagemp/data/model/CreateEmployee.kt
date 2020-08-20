package com.example.cybagemp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * data class for creating EMployee instance
 */
data class CreateEmployee(
    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("salary")
    val salary: String,

    @Expose
    @SerializedName("age")
    val age: String,

    @Expose
    @SerializedName("id")
    val id: String

)