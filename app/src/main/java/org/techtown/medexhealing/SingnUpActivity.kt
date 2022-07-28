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
        var isCheck = false

        val rgbinding = ActivitySingnUpBinding.inflate(layoutInflater)
        setContentView(rgbinding.root)

        var retrofit = Retrofit.Builder()
            .baseUrl("http://220.149.244.199:3001/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var registerService = retrofit.create(RegisterService::class.java)

        rgbinding.btnSignup.setOnClickListener{
            var uid = rgbinding.etId.text.toString()
            var upw = rgbinding.etPw.text.toString()
            var upc = rgbinding.etCheckpw.text.toString()
            var uph = rgbinding.etPhonenum.text.toString()
            var usn = rgbinding.etSerialnum.text.toString()
            var uame = rgbinding.etName.text.toString()
            var dialog = AlertDialog.Builder(this@SingnUpActivity)
            var phoneNum = rgbinding.etPhonenum.text.toString()
            val intent = Intent(this@SingnUpActivity,LoginActivity::class.java)
            Log.d("회원가입 정보","uid : $uid")
            Log.d("회원가입 정보","upw : $upw")
            Log.d("회원가입 정보","upc : $upc")
            Log.d("회원가입 정보","uph : $uph")
            Log.d("회원가입 정보","usn : $usn")
            Log.d("회원가입 정보","usn : $uame")

            if(uid.isEmpty()||upw.isEmpty()||uph.isEmpty()||upc.isEmpty()||usn.isEmpty()||uame.isEmpty()){
                isExistBlack = true
                Log.d("회원가입 정보 확인","빈칸있음")
            }
            else if(upw == upc){
                isPWSame = true
                Log.d("회원가입 정보 확인","비밀번호 중복 성공")
                if(rgbinding.checkBox.isChecked){
                    isCheck = true
                    Log.d("회원가입 정보 확인","약관동의")
                }
            }

            if(!isExistBlack && isPWSame && isCheck){
                Log.d("Main"," id: $uid, pw: $upw, phone: $uph, serial: $usn, name: $uame")

                registerService.requestRegister(uid,upw,uph,usn,uame).enqueue(object: Callback<Register> {

                    override fun onResponse(call: Call<Register>, response: Response<Register>) {
                        val register = response.body()
                        Log.d("회원가입 성공","msg : "+register?.msg)
                        Log.d("회원가입 성공","code : "+register?.code)

                        // 전화번호 저장
                        MySharedPreferences.setPhoneNum(this@SingnUpActivity, phoneNum)

                        dialog.setTitle("회원가입 성공")
                        dialog.setMessage(register?.msg)
                        dialog.show()
                        startActivity(intent)


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
                if(!isExistBlack){
                    dialog("blank")
                    Log.d("회원가입 정보 재확인","빈칸있음")
                }
                else if(!isPWSame){
                    dialog("not same")
                    Log.d("회원가입 정보 재확인","비밀번호중복실패")
                }
                else if(!isCheck){
                    dialog("not check")
                    Log.d("회원가입 정보 재확인","약관동의x")
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
        else if(type.equals("not check")){
            dialog.setTitle("회원가입 실패")
            dialog.setMessage("약관에 동의해주세요")
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