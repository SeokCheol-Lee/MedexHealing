package org.techtown.medexhealing

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TimePicker
import androidx.databinding.DataBindingUtil
import com.example.alarmpr.AlertReceiver
import com.example.alarmpr.TimePickerFragment
import org.techtown.medexhealing.databinding.ActivityAlarmBinding
import java.text.DateFormat
import java.util.*

class AlarmActivity : AppCompatActivity(),  TimePickerDialog.OnTimeSetListener {

    private lateinit var alarmbinding: ActivityAlarmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

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