package org.techtown.medexhealing

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lgbinding = org.techtown.medexhealing.databinding.ActivityLoginBinding.inflate(layoutInflater)
        setContentView(lgbinding.root)
        var name: String = "홍길동"
        var serial: String = "0000"

        lgbinding.fdpass.setOnClickListener {
            val intent = Intent(this,FindActivity::class.java)
            startActivity(intent)
        }
/*
        lgbinding.loginBtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
*/

        lgbinding.btnRegister.setOnClickListener {
            val intent = Intent(this,SingnUpActivity::class.java)
            startActivity(intent)
        }

        var retrofit = Retrofit.Builder()
            .baseUrl("http://220.149.244.199:3001/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var loginService = retrofit.create(LoginService::class.java)

        if(MySharedPreferences.getUserCheck(this) == true){
            var dialog = AlertDialog.Builder(this@LoginActivity)
            Log.d("자동로그인","체크확인")
            var uid = MySharedPreferences.getUserId(this)
            var upw = MySharedPreferences.getUserPass(this)
            val intent = Intent(this,HomeActivity::class.java)
            Log.d("자동로그인","id: $uid, pw: $upw")
            loginService.requestLogin(uid,upw).enqueue(object: Callback<Login> {
                override fun onFailure(call: Call<Login>, t: Throwable) {
                    Log.d("로그인 실패","${t.localizedMessage}")
                    dialog.setTitle("에러")
                    dialog.setMessage("호출에 실패하였습니다")
                    dialog.show()
                }

                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    val login = response.body()

                    if(login?.code == 200){
                        Log.d("로그인 성공","msg : "+login?.msg)
                        Log.d("로그인 성공","code : "+login?.code)
                        Log.d("로그인 성공","name : "+login?.name)
                        Log.d("로그인 성공","serial : "+login?.serialnum)
                        MySharedPreferences.setUserId(this@LoginActivity,uid)
                        MySharedPreferences.setUserPass(this@LoginActivity,upw)
                        name = login.name
                        serial = login.serialnum
                        MySharedPreferences.setUserName(this@LoginActivity, name)
                        MySharedPreferences.setUserSerial(this@LoginActivity, serial)
                        startActivity(intent)
                        finish()
                    }
                    else {
                        MySharedPreferences.clearUser(this@LoginActivity)
                        Log.d("자동로그인","유저삭제 전 id: $uid, pw: $upw")
                        Log.d("자동로그인","유저삭제")
                        Log.d("존재하지 않는 아이디","msg : "+login?.msg)
                        Log.d("존재하지 않는 아이디","code : "+login?.code)
                        dialog.setTitle("로그인 실패")
                        dialog.setMessage("존재하지 않는 아이디입니다")
                        dialog.show()
                    }

                }
            })
        }

        lgbinding.loginBtn.setOnClickListener{
            val intent = Intent(this,HomeActivity::class.java)
            var dialog = AlertDialog.Builder(this@LoginActivity)


            var uid = lgbinding.etLoginid.text.toString()
            var upw = lgbinding.etLoginpw.text.toString()

            if(uid.isEmpty()){
                dialog.setTitle("로그인 실패")
                dialog.setMessage("아이디를 입력하세요")
                dialog.show()
            }
            else if(upw.isEmpty()){
                dialog.setTitle("로그인 실패")
                dialog.setMessage("비밀번호를 입력하세요")
                dialog.show()
            }

            Log.d("Main","id: $uid, pw: $upw")

            if(lgbinding.autologin.isChecked){
                MySharedPreferences.setUserCheck(this,true)
                Log.d("자동로그인","체크됨")
                MySharedPreferences.setUserId(this@LoginActivity,uid)
                MySharedPreferences.setUserPass(this@LoginActivity,upw)
                Log.d("자동로그인","유저정보 저장")
            }
            else{
                MySharedPreferences.setUserCheck(this,false)
                Log.d("자동로그인","체크안됨")
            }

            loginService.requestLogin(uid,upw).enqueue(object: Callback<Login> {
                override fun onFailure(call: Call<Login>, t: Throwable) {
                    Log.d("로그인 실패","${t.localizedMessage}")
                    dialog.setTitle("에러")
                    dialog.setMessage("호출에 실패하였습니다")
                    dialog.show()
                }

                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    val login = response.body()

                    if(login?.code == 200){
                        Log.d("로그인 성공","msg : "+login?.msg)
                        Log.d("로그인 성공","code : "+login?.code)
                        Log.d("로그인 성공","name : "+login?.name)
                        Log.d("로그인 성공","serial : "+login?.serialnum)
                        name = login.name
                        serial = login.serialnum
                        MySharedPreferences.setUserName(this@LoginActivity, name)
                        MySharedPreferences.setUserSerial(this@LoginActivity, serial)
                        startActivity(intent)
                    }
                    else {
                        MySharedPreferences.removeUser(this@LoginActivity)
                        Log.d("존재하지 않는 아이디","msg : "+login?.msg)
                        Log.d("존재하지 않는 아이디","code : "+login?.code)
                        dialog.setTitle("로그인 실패")
                        dialog.setMessage("존재하지 않는 아이디입니다")
                        dialog.show()
                    }

                }
            })

        }


    }

}