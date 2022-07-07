package com.example.alarmpr

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.media.MediaPlayer
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import org.techtown.medexhealing.Alarm.Alarm
import org.techtown.medexhealing.Login
import org.techtown.medexhealing.LoginService
import org.techtown.medexhealing.R
import org.techtown.medexhealing.databinding.ActivityAlarmBinding
import org.techtown.medexhealing.databinding.ActivitySplashBinding
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NotificationHelper(base: Context?) : ContextWrapper(base){
    private val channelID = "channelID"
    private val channelNm = "channelNm"
    val mediaPlayer = MediaPlayer.create(this,R.raw.mi)
    val intent: Intent = Intent(this, ActivitySplashBinding::class.java).apply {
        Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent: PendingIntent = PendingIntent.getActivity(this,0, intent, 0)

    init{
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            createChannel()
        }
    }

    private fun createChannel() {
        //var channel = NotificationChannel(channelID,channelNm, NotificationManager.IMPORTANCE_DEFAULT)
        var channel = NotificationChannel(channelID,channelNm,NotificationManager.IMPORTANCE_DEFAULT)

        channel.enableLights(true)
        channel.enableVibration(true)
        channel.lightColor = Color.GREEN
        channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE

        getManager().createNotificationChannel(channel)
        Log.d("알람","알람 채널 생성")
    }

    fun getManager() : NotificationManager{
        return getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    }
    fun getChannelNotification(time: String?) : NotificationCompat.Builder{

        Log.d("알람","알람울림")
        mediaPlayer.start()

        return NotificationCompat.Builder(applicationContext, channelID)
            .setContentTitle(time)
            .setContentText("일어날 시간입니다")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
    }

}

