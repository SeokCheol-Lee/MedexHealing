package org.techtown.medexhealing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.techtown.medexhealing.databinding.ActivityUserInfoBinding

class UserInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val uibinding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(uibinding.root)

        var name = MySharedPreferences.getUserName(this)
        uibinding.tvUsname.setText(name+"님")

        uibinding.btnAlarmSetting.setOnClickListener{
            val alarmintent: Intent = Intent(this,AlarmActivity::class.java)
            startActivity(alarmintent)
        }
    }
}