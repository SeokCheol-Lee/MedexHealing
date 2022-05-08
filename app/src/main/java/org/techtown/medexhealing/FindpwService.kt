package org.techtown.medexhealing

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface FindpwService {

    @FormUrlEncoded
    @POST("app_list/app1")
    fun requestFindpw(
        @Field("userph") userph:String
    ) : Call<Findpw>
}