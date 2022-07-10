package org.techtown.medexhealing.Fragment

import org.techtown.medexhealing.Alarm.Alarm
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ControlService {
    @FormUrlEncoded
    @POST("app_list/wake_up_check")
    fun requestcon(
        @Field("serialnum") serialnum:String,
        @Field("btnnum") btnnum:String
    ) : Call<Modecon>
}