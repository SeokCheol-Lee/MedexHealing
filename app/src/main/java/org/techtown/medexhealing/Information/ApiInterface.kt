package org.techtown.medexhealing.Information

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @POST("app_list/information/deep_sleep")
    fun getData(): Call<List<MyDataItem>>

    @POST("app_list/information/breathe")
    fun getBreatheData(): Call<List<MyDataItem>>

    @POST("app_list/information/snore")
    fun getSnoreData(): Call<List<MyDataItem>>

    @POST("app_list/information/stretch")
    fun getStretchData(): Call<List<MyDataItem>>
}