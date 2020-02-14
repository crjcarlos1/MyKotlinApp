package com.cralos.mykotlinapp2.fragments.mvp.example2.interfaces

import com.cralos.mykotlinapp2.fragments.mvp.example2.models.User
import retrofit2.Call
import retrofit2.http.GET

interface UsersApi {
    /***
     *
     */
    @GET("/users")
    fun getUsers(): Call<List<User>>

}