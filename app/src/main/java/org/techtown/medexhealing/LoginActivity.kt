package org.techtown.medexhealing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.techtown.medexhealing.databinding.ActivityFindBinding
import org.techtown.medexhealing.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lgbinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(lgbinding.root)

        lgbinding.fdpass.setOnClickListener {
            val intent = Intent(this,FindActivity::class.java)
            startActivity(intent)
        }
        lgbinding.loginBtn.setOnClickListener {
            val intent = Intent(this, UserSelectActivity::class.java)
            startActivity(intent)
        }

    }


}