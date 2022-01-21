package org.techtown.medexhealing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.techtown.medexhealing.databinding.ActivityFindBinding

class FindActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fnbinding = ActivityFindBinding.inflate(layoutInflater)
        setContentView(fnbinding.root)

        fnbinding.gotologinBtn.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}