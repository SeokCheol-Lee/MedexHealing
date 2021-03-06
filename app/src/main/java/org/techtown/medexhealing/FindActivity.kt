package org.techtown.medexhealing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import org.techtown.medexhealing.databinding.ActivityFindBinding
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FindActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fnbinding = ActivityFindBinding.inflate(layoutInflater)
        setContentView(fnbinding.root)
        val intent: Intent = Intent(this, LoginActivity::class.java)

        var retrofit = Retrofit.Builder()
            .baseUrl("http://220.149.244.199:3001/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var findpwService = retrofit.create(FindpwService::class.java)
        var modifypwService = retrofit.create(ModifypwService::class.java)
        var mpw : String = "010"

        fnbinding.gotologinBtn.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        fnbinding.succBtn.setOnClickListener {
            var uph = fnbinding.ipphone.text.toString()
            var uid = fnbinding.etFpid.text.toString()
            var dialog = AlertDialog.Builder(this@FindActivity)
            var mpdialog = ModifyDialog(this)
            if (uph.isEmpty()||uid.isEmpty()){
                dialog.setTitle("비밀번호 찾기 실패")
                dialog.setMessage("빈칸을 기입해주세요")
                dialog.show()
            }
            Log.d("Findpw","ph : $uph")

            findpwService.requestFindpw(uid,uph).enqueue(object: Callback<Findpw>{
                override fun onFailure(call: Call<Findpw>, t: Throwable) {
                    Log.d("비밀번호 찾기 실패","${t.localizedMessage}")
                    dialog.setTitle("에러")
                    dialog.setMessage("호출에 실패하였습니다")
                    dialog.show()
                }

                override fun onResponse(call: Call<Findpw>, response: Response<Findpw>) {
                    val findpw = response.body()
                    Log.d("비밀번호 찾기 성공","msg : "+findpw?.msg)
                    Log.d("비밀번호 찾기 성공","code : "+findpw?.code)
                    dialog.setTitle("비밀번호 찾기 성공")
                    dialog.setMessage(findpw?.msg)
                    dialog.show()
                    mpdialog.showDialog()
                    mpdialog.setOnClickListener(object : ModifyDialog.OnDialogClickListener{
                        override fun onClicked(modifypw: String) {
                            mpw = modifypw
                            Log.d("비밀번호 찾기 성공","mpw: $mpw")
                            if(findpw?.code == 200){
                                modifypwService.requestModifypw(mpw).enqueue(object : Callback<Modifypw>{
                                    override fun onFailure(call: Call<Modifypw>, t: Throwable) {
                                        Log.d("비밀번호 변경 실패",t.localizedMessage)
                                        Toast.makeText(this@FindActivity,"서버와 연결이 원활하지 않습니다",Toast.LENGTH_LONG)

                                    }
                                    override fun onResponse(call: Call<Modifypw>, response: Response<Modifypw>) {
                                        val modifypw = response.body()
                                        Log.d("비밀번호 변경 성공","msg : "+modifypw?.msg)
                                        Toast.makeText(this@FindActivity,"비밀번호 변경에 성공하셨습니다",Toast.LENGTH_LONG)

                                    }

                                })
                            }
                        }

                    })


                }
            })

        }

    }
}