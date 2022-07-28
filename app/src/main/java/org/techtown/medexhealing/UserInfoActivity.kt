package org.techtown.medexhealing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.techtown.medexhealing.Survey.SurveyActivity
import org.techtown.medexhealing.databinding.ActivityUserInfoBinding


class UserInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val uibinding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(uibinding.root)

        // local data 불러오기
        var name = MySharedPreferences.getUserName(this)
        uibinding.tvUsname.setText(name+"님")
        ///

        // Intent
        uibinding.btnAlarmSetting.setOnClickListener{
            val alarmintent: Intent = Intent(this,AlarmActivity::class.java)
            startActivity(alarmintent)
        }
        uibinding.btnSurvey.setOnClickListener {
            val surintent: Intent = Intent(this, SurveyActivity::class.java)
            startActivity(surintent)
        }
        uibinding.btnInfoModify.setOnClickListener {
            val modifyintent: Intent = Intent(this, ChangeInfoActivity::class.java)
            startActivity(modifyintent)
        }
        ///


    }
}