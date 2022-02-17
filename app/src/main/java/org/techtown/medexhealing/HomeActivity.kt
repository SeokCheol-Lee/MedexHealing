package org.techtown.medexhealing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.techtown.medexhealing.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val hmbinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(hmbinding.root)

        hmbinding.btnModselect.setOnClickListener{
            val msintent = Intent(this, ModSelectActivity::class.java)
            startActivity(msintent)
        }
        hmbinding.btnInfo.setOnClickListener{
            val ifintent = Intent(this, InformationActivity::class.java)
            startActivity(ifintent)
        }
        hmbinding.btnMyinfo.setOnClickListener{
            val uiintent = Intent(this, UserInfoActivity::class.java)
            startActivity(uiintent)
        }
        hmbinding.btnPairing.setOnClickListener {
            val paintent = Intent(this, EspTouchActivity::class.java)
            startActivity(paintent)
        }
    }
}