package org.techtown.medexhealing

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface FindpwService {

    @FormUrlEncoded
    @POST("app_list/pw_1")
    fun requestFindpw(
        @Field("id") id:String,
        @Field("ph") ph:String
    ) : Call<Findpw>
}