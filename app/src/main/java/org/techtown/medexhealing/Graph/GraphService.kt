package org.techtown.medexhealing.Graph

import org.techtown.medexhealing.Findpw
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface GraphService {
    @FormUrlEncoded
    @POST("app_list/pw_1")
    fun requestgraph(
        @Field("serialnum") serialnum:String
    ) : Call<Graph>
}