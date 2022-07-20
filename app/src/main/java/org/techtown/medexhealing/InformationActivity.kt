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

        binding.breatheBtn.setOnClickListener {
            var intent = Intent(this, InformationBreatheActivity::class.java)
            startActivity(intent)
        }

        binding.stretchBtn.setOnClickListener {
            var intent = Intent(this, InformationStretchActivity::class.java)
            startActivity(intent)
        }

        binding.snoreBtn.setOnClickListener {
            var intent = Intent(this, InformationSnoreActivity::class.java)
            startActivity(intent)
        }
    }
}