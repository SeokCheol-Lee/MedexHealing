package org.techtown.medexhealing

import android.content.DialogInterface
import android.content.Intent
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

        var isExistBlack = false
        var isPWSame = false

        val rgbinding = ActivitySingnUpBinding.inflate(layoutInflater)
        setContentView(rgbinding.root)

        var retrofit = Retrofit.Builder()
            .baseUrl("http://220.149.236.48:3001/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var registerService = retrofit.create(RegisterService::class.java)

        rgbinding.btnSignup.setOnClickListener{
            var uid = rgbinding.etId.text.toString()
            var upw = rgbinding.etPw.text.toString()
            var upc = rgbinding.etCheckpw.text.toString()
            var uph = rgbinding.etPhonenum.text.toString()
            var dialog = AlertDialog.Builder(this@SingnUpActivity)
            val intent = Intent(this@SingnUpActivity,LoginActivity::class.java)

            if(uid.isEmpty()||upw.isEmpty()||uph.isEmpty()||upc.isEmpty()){
                isExistBlack = true
            }
            else{
                if(upw == upc){
                    isPWSame = true
                }
            }

            if(!isExistBlack && isPWSame){
                Log.d("Main"," id: $uid, pw: $upw, phone: $uph")

                registerService.requestRegister(uid,upw,uph).enqueue(object: Callback<Register> {

                    override fun onResponse(call: Call<Register>, response: Response<Register>) {
                        val register = response.body()
                        Log.d("회원가입 성공","msg : "+register?.msg)
                        Log.d("회원가입 성공","code : "+register?.code)
                        dialog.setTitle("회원가입 성공")
                        dialog.setMessage(register?.msg)
                        dialog.show()

                        if(register?.code == 100){
                            Log.d("회원가입 성공","intent성공")
                            startActivity(intent)
                        }

                    }

                    override fun onFailure(call: Call<Register>, t: Throwable) {
                        Log.d("회원가입 실패","${t.localizedMessage}")
                        dialog.setTitle("에러")
                        dialog.setMessage("호출에 실패하였습니다")
                        dialog.show()
                    }

                })
            }
            else{
                if(isExistBlack){
                    dialog("blank")
                }
                else if(!isPWSame){
                    dialog("not same")
                }

            }

        }
    }
    fun dialog(type: String){
        val dialog = AlertDialog.Builder(this)

        // 작성 안한 항목이 있을 경우
        if(type.equals("blank")){
            dialog.setTitle("회원가입 실패")
            dialog.setMessage("입력란을 모두 작성해주세요")
        }
        // 입력한 비밀번호가 다를 경우
        else if(type.equals("not same")){
            dialog.setTitle("회원가입 실패")
            dialog.setMessage("비밀번호가 다릅니다")
        }

        val dialog_listener = object: DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                when(which){
                    DialogInterface.BUTTON_POSITIVE ->
                        Log.d("회원가입", "다이얼로그")
                }
            }
        }

        dialog.setPositiveButton("확인",dialog_listener)
        dialog.show()
    }
}