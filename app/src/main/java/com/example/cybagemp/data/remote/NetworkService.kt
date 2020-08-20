package com.example.cybagemp.data.remote

import com.example.cybagemp.data.remote.request.CreateEmployeeRequest
import com.example.cybagemp.data.remote.request.EmployeeDetailsRequest
import com.example.cybagemp.data.remote.response.CreatedEmployeeResponse
import com.example.cybagemp.data.remote.response.EmployeeDeleteResponse
import com.example.cybagemp.data.remote.response.EmployeeDetailsResponse
import com.example.cybagemp.data.remote.response.EmployeeListResponse
import io.reactivex.Single
import retrofit2.http.*
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET(Endpoints.HOME_EMP_LIST)
    fun doHomeEmployeeListCall(): Single<EmployeeListResponse>


    @GET(Endpoints.EMPLOYEE_DETAILS)
    fun doEmployeeDetailsCall(@Path("id") id: String): Single<EmployeeDetailsResponse>

    @PUT(Endpoints.EMPLOYEE_UPDATE)
    fun doUpdateEmployeeDetailsCall(@Path("id") id: String,
    @Body request: EmployeeDetailsRequest): Single<EmployeeDetailsResponse>

    @DELETE(Endpoints.EMPLOYEE_DELETE)
    fun doDeleteEmployeeCall(@Path("id") id: String): Single<EmployeeDeleteResponse>

    @POST(Endpoints.EMPLOYEE_CREATE)
    fun doCreateEmployeeCall(@Body request: CreateEmployeeRequest): Single<CreatedEmployeeResponse>
}