package org.techtown.medexhealing

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import org.techtown.medexhealing.databinding.ActivityLoginBinding
import org.techtown.medexhealing.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
        lgbinding.btnRegister.setOnClickListener {
            val intent = Intent(this,SingnUpActivity::class.java)
            startActivity(intent)
        }


//        var retrofit = Retrofit.Builder()
//            .baseUrl("http://220.149.236.48:3001/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        var loginService = retrofit.create(LoginService::class.java)
//
//        lgbinding.loginBtn.setOnClickListener{
//            var uid = lgbinding.etLoginid.text.toString()
//            var upw = lgbinding.etLoginpw.text.toString()
//
//
//            Log.d("Main","id: $uid, pw: $upw")
//
//            loginService.requestLogin(uid,upw).enqueue(object: Callback<Login> {
//                override fun onFailure(call: Call<Login>, t: Throwable) {
//                    var dialog = AlertDialog.Builder(this@LoginActivity)
//                    Log.d("로그인 실패","${t.localizedMessage}")
//                    dialog.setTitle("에러")
//                    dialog.setMessage("호출에 실패하였습니다")
//                    dialog.show()
//                }
//
//                override fun onResponse(call: Call<Login>, response: Response<Login>) {
//                    val login = response.body()
//                    var dialog = AlertDialog.Builder(this@LoginActivity)
//                    Log.d("로그인 성공","msg : "+login?.msg)
//                    Log.d("로그인 성공","msg : "+login?.code)
//                    dialog.setTitle(login?.msg)
//                    dialog.setMessage(login?.code)
//                    dialog.show()
//
//                }
//            })
//
//        }


    }


}