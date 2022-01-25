package com.wgy.recyclemanager.network

import com.wgy.recyclemanager.model.LoginResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginService {
    @GET("loginData.json")
    fun getLoginData(@Query("username") username: String,@Query("password") password: String): Call<LoginResponse>
}