package org.techtown.medexhealing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import org.techtown.medexhealing.databinding.ActivityLoginBinding
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

        lgbinding.loginBtn.setOnClickListener{
            var uid = lgbinding.etLoginid.text.toString()
            var upw = lgbinding.etLoginpw.text.toString()
            val intent = Intent(this,HomeActivity::class.java)
            var dialog = AlertDialog.Builder(this@LoginActivity)

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
            else{
                dialog.setTitle("에러")
                dialog.setMessage("로그인에 실패하였습니다")
                dialog.show()
            }

            //startActivity(intent)


            Log.d("Main","id: $uid, pw: $upw")

            if(lgbinding.autologin.isChecked){

                if(MySharedPreferences.getUserId(this).isNullOrBlank()
                    || MySharedPreferences.getUserPass(this).isNullOrBlank()) {
                    Login()
                }
                else { // SharedPreferences 안에 값이 저장되어 있을 때 -> MainActivity로 이동
                    uid = MySharedPreferences.getUserId(this)
                    upw = MySharedPreferences.getUserPass(this)
                }
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
                    var dialog = AlertDialog.Builder(this@LoginActivity)


                    if(login?.code == 100){
                        Log.d("로그인 성공","msg : "+login?.msg)
                        Log.d("로그인 성공","msg : "+login?.code)
                        MySharedPreferences.setUserId(this@LoginActivity,lgbinding.etLoginid.toString())
                        MySharedPreferences.setUserPass(this@LoginActivity,lgbinding.etLoginpw.toString())
                        dialog.setTitle(login?.msg)
                        dialog.setMessage(login?.code)
                        dialog.show()
                        startActivity(intent)
                        finish()
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
    fun Login() {
        val btn_login = findViewById<Button>(R.id.login_btn)
        val et_id : EditText = findViewById(R.id.et_loginid)
        val et_pass : EditText = findViewById(R.id.et_loginpw)

        btn_login.setOnClickListener {

            MySharedPreferences.setUserId(this, et_id.text.toString())
            MySharedPreferences.setUserPass(this, et_pass.text.toString())

        }
    }

}
