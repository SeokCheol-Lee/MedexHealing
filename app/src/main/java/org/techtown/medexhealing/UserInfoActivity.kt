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

        if (intent.hasExtra("uiname")){
            uibinding.tvUsname.setText(intent.getStringExtra("uiname") + "님")
        }else{
            Toast.makeText(this,"Error!", Toast.LENGTH_LONG).show()
        }

        uibinding.btnAlarmSetting.setOnClickListener {
            val intent = Intent(this,AlarmPlusActivity::class.java)
            startActivity(intent)
        }
        uibinding.btnSurvey.setOnClickListener {
            val intent = Intent(this, SurveyActivity::class.java)
            startActivity(intent)
        }
        uibinding.btnInfoModify.setOnClickListener {
            val intent = Intent(this, ChangeInfoActivity::class.java)
            startActivity(intent)
        }
    }
}