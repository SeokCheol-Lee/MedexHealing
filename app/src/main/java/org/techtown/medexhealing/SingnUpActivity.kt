package org.techtown.medexhealing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import org.techtown.medexhealing.databinding.ActivitySingnUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SingnUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rgbinding = ActivitySingnUpBinding.inflate(layoutInflater)
        setContentView(rgbinding.root)

        var retrofit = Retrofit.Builder()
            .baseUrl("http://220.149.236.48:3001/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var registerService = retrofit.create(RegisterService::class.java)

        rgbinding.btnSignup.setOnClickListener{
            var sir = rgbinding.etSiralnum.text.toString()
            var uid = rgbinding.etId.text.toString()
            var upw = rgbinding.etPw.text.toString()
            var uph = rgbinding.etPhonenum.text.toString()


            Log.d("Main","serial: $sir id: $uid, pw: $upw, phone: $uph")

            registerService.requestRegister(sir,uid,upw,uph).enqueue(object: Callback<Register> {
                override fun onResponse(call: Call<Register>, response: Response<Register>) {
                    val register = response.body()
                    var dialog = AlertDialog.Builder(this@SingnUpActivity)
                    Log.d("회원가입 성공","msg : "+register?.msg)
                    Log.d("회원가입 성공","msg : "+register?.code)
                    dialog.setTitle(register?.msg)
                    dialog.setMessage(register?.code)
                    dialog.show()
                }

                override fun onFailure(call: Call<Register>, t: Throwable) {
                    var dialog = AlertDialog.Builder(this@SingnUpActivity)
                    Log.d("회원가입 실패","${t.localizedMessage}")
                    dialog.setTitle("에러")
                    dialog.setMessage("호출에 실패하였습니다")
                    dialog.show()
                }

            })


        }
    }
}