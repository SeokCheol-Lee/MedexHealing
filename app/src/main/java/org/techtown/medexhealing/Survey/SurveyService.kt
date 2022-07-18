package org.techtown.medexhealing.Survey

import org.techtown.medexhealing.Findpw
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SurveyService {
    @FormUrlEncoded
    @POST("app_list/survey")
    fun requestSurvey(
        @Field("serialnum") serialnum:String,
        @Field("gender") gender:String,
        @Field("birth") birth:String,
        @Field("height") height:String,
        @Field("weight") weight:String,
        @Field("sleeptime") sleeptime:String,
        @Field("wakeuptime") wakeuptime:String,
        @Field("sickness") sickness:String,
        @Field("satisfaction") satisfaction:String
    ) : Call<Survey>
}