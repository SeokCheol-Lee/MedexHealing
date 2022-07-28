package org.techtown.medexhealing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import org.techtown.medexhealing.databinding.ActivityChangeInfoBinding

class ChangeInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_info)

        val uibinding = ActivityChangeInfoBinding.inflate(layoutInflater)
        setContentView(uibinding.root)

        var userPhoneNum = MySharedPreferences.getPhoneNum(this)
        var upw = MySharedPreferences.getUserPass(this)


        uibinding.showPhoneNum.setText(userPhoneNum[0].toString()+userPhoneNum[1].toString()+userPhoneNum[2].toString()+"-"+userPhoneNum[3].toString()+userPhoneNum[4].toString()+userPhoneNum[5].toString()+userPhoneNum[6].toString()+"-"+userPhoneNum[7].toString()+userPhoneNum[8].toString()+userPhoneNum[9].toString()+userPhoneNum[10].toString())



        uibinding.btnChange.setOnClickListener {
            var pw = uibinding.editPW.text.toString()
            var phoneNum = uibinding.newPhoneNum.text.toString()

            if (pw == upw){
                MySharedPreferences.setPhoneNum(this, phoneNum)
                uibinding.newPhoneNum.setText("")
                uibinding.editPW.setText("")
                Toast.makeText(this, "전화번호 변경 성공", Toast.LENGTH_LONG).show()
                val intent: Intent = Intent(this, UserInfoActivity::class.java)
                startActivity(intent)
                Log.d("TAG", "전화번호 변경 성공")
            }
            else{
                uibinding.newPhoneNum.setText("")
                uibinding.editPW.setText("")
                Log.d("TAG", "전화번호 변경 실패")
            }

        }



    }
}