package com.example.cybagemp.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EmployeeDeleteResponse(
    @Expose
    @SerializedName("status")
    var status: String,

    @Expose
    @SerializedName("data")
    val data: String,

    @Expose
    @SerializedName("message")
    val message: String
)