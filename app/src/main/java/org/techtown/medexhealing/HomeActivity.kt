package org.techtown.medexhealing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.techtown.medexhealing.Graph.GraphActivity
import org.techtown.medexhealing.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val hmbinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(hmbinding.root)

        var name = MySharedPreferences.getUserName(this)

        hmbinding.tvHome.setText("안녕하세요"+name+"님 :)")


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
            intent.putExtra("uiname",intent.getStringExtra("name"))
            startActivity(uiintent)
        }
        hmbinding.btnPairing.setOnClickListener {
            val printent = Intent(this,EspTouchActivity::class.java)
            startActivity(printent)
        }
        hmbinding.btnBedinfo.setOnClickListener {
            val intent = Intent(this, GraphActivity::class.java)
            startActivity(intent)
        }
    }
}