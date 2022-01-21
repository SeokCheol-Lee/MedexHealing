package org.techtown.medexhealing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import org.techtown.medexhealing.databinding.ActivityPairingBinding

class PairingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var prbinding = ActivityPairingBinding.inflate(layoutInflater)
        setContentView(prbinding.root)

        Handler().postDelayed({
            prbinding.pairimg.setImageResource(R.drawable.wifi_blue)
        },3000)

        Handler().postDelayed({
            val intent: Intent = Intent(this,LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        },6000)
    }
}