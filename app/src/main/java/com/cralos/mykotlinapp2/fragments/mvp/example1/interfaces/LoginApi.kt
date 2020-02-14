package com.cralos.mykotlinapp2.fragments.mvp.example1.interfaces

import com.cralos.mykotlinapp2.fragments.mvp.example1.models.LoginRequest
import com.cralos.mykotlinapp2.fragments.mvp.example1.models.LoginResponse
import com.cralos.mykotlinapp2.fragments.mvp.example1.retrofit.EndPoints
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    /**
     *
     */
    @POST(EndPoints.LOGIN)
    fun login(@Body request: LoginRequest): Call<LoginResponse>

}