package org.techtown.medexhealing


import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RegisterService {

    @FormUrlEncoded
    @POST("app_list/sign_up")
    fun requestRegister(
        @Field("userid") userid:String,
        @Field("userpw") userpw:String,
        @Field("userph") userph:String
    ) : Call<Register>
}