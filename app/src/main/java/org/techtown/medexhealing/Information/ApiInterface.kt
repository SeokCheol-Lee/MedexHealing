package org.techtown.medexhealing.Information

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @POST("app_list/information/deep_sleep")
    fun getData(): Call<List<MyDataItem>>
}