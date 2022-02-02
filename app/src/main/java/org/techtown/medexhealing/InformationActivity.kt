package org.techtown.medexhealing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.techtown.medexhealing.databinding.ActivityInformationBinding

class InformationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_information)

        binding.sleepBtn.setOnClickListener {
            var intent = Intent(this, InformationSleepActivity::class.java)
            startActivity(intent)
        }
    }
}