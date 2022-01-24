package org.techtown.medexhealing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.techtown.medexhealing.databinding.ActivityUserCreateBinding

class UserCreateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ucbindng = ActivityUserCreateBinding.inflate(layoutInflater)
        setContentView(ucbindng.root)

        ucbindng.button4.setOnClickListener {
            val intent = Intent(this, UserSelectActivity::class.java)
            startActivity(intent)
        }
    }
}