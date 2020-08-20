package com.example.cybagemp.data.remote.response

import com.example.cybagemp.data.model.CreateEmployee
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * data class for employee rsponse
 */
data class CreatedEmployeeResponse(
    @Expose
    @SerializedName("status")
    var status: String,

    @Expose
    @SerializedName("data")
    val data: CreateEmployee
)