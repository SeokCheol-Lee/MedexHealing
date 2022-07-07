package org.techtown.medexhealing

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TimePicker
import androidx.databinding.DataBindingUtil
import com.example.alarmpr.AlertReceiver
import com.example.alarmpr.TimePickerFragment
import org.techtown.medexhealing.Alarm.Alarm
import org.techtown.medexhealing.Alarm.AlarmService
import org.techtown.medexhealing.databinding.ActivityAlarmBinding
import org.techtown.medexhealing.databinding.ActivityFindBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DateFormat
import java.util.*

class AlarmActivity : AppCompatActivity(),  TimePickerDialog.OnTimeSetListener {

    private lateinit var alarmbinding: ActivityAlarmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        var retrofit = Retrofit.Builder()
            .baseUrl("http://220.149.244.199:3001/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var alarmService = retrofit.create(AlarmService::class.java)
        val ak = "wake"
        val sid = "32131"

        alarmService.requestalarm(sid,ak).enqueue(object: Callback<Alarm>{
            override fun onResponse(call: Call<Alarm>, response: Response<Alarm>) {
                val ald = response.body()
                Log.d("알람 송신","msg : "+ald?.msg)
                Log.d("알람 송신","code : "+ald?.code)
            }

            override fun onFailure(call: Call<Alarm>, t: Throwable) {
                Log.d("알람 연결 실패","${t.localizedMessage}")
            }

        } )

        alarmbinding = DataBindingUtil.setContentView(this,R.layout.activity_alarm)
        alarmbinding.timeBtn.setOnClickListener {

            var timePicker = TimePickerFragment()
            timePicker.show(supportFragmentManager, "Time Picker")

        }
        alarmbinding.alarmCancelBtn.setOnClickListener{
            cancleAlarm()

        }
    }
    override fun onTimeSet(p0: TimePicker?, hourofDay: Int, minute: Int) {
        var c = Calendar.getInstance()

        c.set(Calendar.HOUR_OF_DAY, hourofDay)
        c.set(Calendar.MINUTE, minute)
        c.set(Calendar.SECOND, 0)

        updateTimeText(c)

        startAlarm(c)
    }
    private fun updateTimeText(c: Calendar){
        var curTime = DateFormat.getTimeInstance(DateFormat.SHORT).format(c.time)

        alarmbinding.timeText.append("알람 시간 : ")
        alarmbinding.timeText.append(curTime)
    }
    private fun startAlarm(c: Calendar){
        var alarmManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        var intent = Intent(this, AlertReceiver::class.java)

        var curTime = DateFormat.getTimeInstance(DateFormat.SHORT).format(c.time)
        intent.putExtra("time",curTime)


        var pendingIntent = PendingIntent.getBroadcast(this,1,intent,0)
        if(c.before(Calendar.getInstance())){
            c.add(Calendar.DATE,1)
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.timeInMillis, pendingIntent)
    }

    private fun cancleAlarm() {
        var alarmManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        var intent = Intent(this, AlertReceiver::class.java)
        var pendingIntent = PendingIntent.getBroadcast(this,1,intent,0)
        alarmManager.cancel(pendingIntent)
        alarmbinding.timeText.text = "알람 취소"
    }
}