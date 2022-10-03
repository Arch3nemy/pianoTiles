package com.alacrity.template.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("/{id}")
    fun getFactAboutNumber(@Path("id") id: Int): Call<ResponseBody>

}