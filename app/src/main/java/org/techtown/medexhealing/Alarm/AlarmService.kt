package org.techtown.medexhealing.Alarm

import org.techtown.medexhealing.Findpw
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AlarmService {
    @FormUrlEncoded
    @POST("app_list/wake_up_check")
    fun requestalarm(
        @Field("wake_up") wakeup:String
    ) : Call<Alarm>
}