package org.techtown.medexhealing.Fragment

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SleepService {
    @FormUrlEncoded
    @POST("app_list/sleep_check")
    fun sleepcheck(
        @Field("serialnum") serialnum:String,
        @Field("btnnum") btnnum:String
    ) : Call<SleepCheck>
}