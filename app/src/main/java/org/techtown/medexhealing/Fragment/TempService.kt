package org.techtown.medexhealing.Fragment

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface TempService {
    @FormUrlEncoded
    @POST("mat_control")
    fun requesttempcon(
        @Field("serialnum") serialnum:String,
        @Field("btnnum") btnnum:String
    ) : Call<Tempcon>
}