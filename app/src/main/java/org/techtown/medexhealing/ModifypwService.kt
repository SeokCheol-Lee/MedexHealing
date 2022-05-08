package org.techtown.medexhealing

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ModifypwService {

    @FormUrlEncoded
    @POST("app_list/app1")
    fun requestModifypw(
        @Field("modifypw") modifypw:String
    ) : Call<Modifypw>
}