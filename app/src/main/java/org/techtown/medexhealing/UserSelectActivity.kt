package org.techtown.medexhealing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import org.techtown.medexhealing.databinding.ActivityUserSelectBinding

class UserSelectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val usbinding = ActivityUserSelectBinding.inflate(layoutInflater)
        setContentView(usbinding.root)

        val man = R.drawable.man
        val woman = R.drawable.woman

        val profileList = arrayListOf(
            Profiles(man,"홍길동"),
            Profiles(woman, "아나스타샤")
        )
        usbinding.rvProfile.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        usbinding.rvProfile.setHasFixedSize(true)

        usbinding.rvProfile.adapter = ProfileAdapter(profileList)

        usbinding.btnAdduser.setOnClickListener {
            val intent = Intent(this, UserCreateActivity::class.java)
            startActivity(intent)
        }

    }

}