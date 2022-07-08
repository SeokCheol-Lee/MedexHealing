package org.techtown.medexhealing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.techtown.medexhealing.Alarm.Alarm
import org.techtown.medexhealing.Alarm.AlarmService
import org.techtown.medexhealing.databinding.ActivityAlarmOpenBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AlarmOpen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val aobinding = ActivityAlarmOpenBinding.inflate(layoutInflater)
        setContentView(aobinding.root)

        aobinding.btnClock.setOnClickListener {
            val intent: Intent = Intent(this,SplashActivity::class.java)
            startActivity(intent)
            finish()
        }

        var retrofit = Retrofit.Builder()
            .baseUrl("http://220.149.244.199:3001/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var alarmService = retrofit.create(AlarmService::class.java)
        val sid = "32131"

        alarmService.requestalarm(sid).enqueue(object: Callback<Alarm> {
            override fun onResponse(call: Call<Alarm>, response: Response<Alarm>) {
                val ald = response.body()
                Log.d("알람 송신","msg : "+ald?.msg)
                Log.d("알람 송신","code : "+ald?.code)
            }

            override fun onFailure(call: Call<Alarm>, t: Throwable) {
                Log.d("알람 연결 실패","${t.localizedMessage}")
            }

        } )
    }
}