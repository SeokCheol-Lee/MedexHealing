package org.techtown.medexhealing.Survey

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import org.techtown.medexhealing.MySharedPreferences
import org.techtown.medexhealing.R
import org.techtown.medexhealing.UserInfoActivity
import org.techtown.medexhealing.databinding.ActivitySurveyBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class SurveyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val surbinding = ActivitySurveyBinding.inflate(layoutInflater)
        setContentView(surbinding.root)

        var serial = MySharedPreferences.getUserSerial(this)

        var name = MySharedPreferences.getUserName(this)
        surbinding.tvSurname.setText(name+" 님의 현재 상태를 작성해주세요")

        //spinner 초기화
        val genlist = listOf("성별을 선택하세요","남성","여성")
        val sicklist = listOf("질병을 선택해주세요","없음","불면증","과수면(기면증)","하지불안증후군","코골이")
        val genadapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, genlist)
        val sickadapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, sicklist)
        surbinding.spinGender.adapter = genadapter
        surbinding.spinSick.adapter = sickadapter

        //만족도 spinner
        var sati = (1..10).toList()
        val satilist = sati.map { it.toString() }
        val satiadapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, satilist)
        surbinding.spinSat.adapter = satiadapter

        //생년월일 선택 초기화
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        var syear: String = ""
        var smonth: String = ""
        var sday: String = ""

        //시간 선택 초기화
        val tim = Calendar.getInstance()
        val tim2 = Calendar.getInstance()

        //설문 결과 초기화
        var gen: String = ""
        var sick: String = ""
        var heigth: String = ""
        var weight: String = ""
        var satisfaction: String = ""
        var birth: String = ""
        var sleeptime: String = "9:00"
        var wakeuptime: String = "9:00"

        surbinding.spinGender.onItemSelectedListener = object :AdapterView.OnItemSelectedListener,
            AdapterView.OnItemClickListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position != 0)
                    Log.d("성별",genlist[position])
                    gen = genlist[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                TODO("Not yet implemented")
            }

        }
        surbinding.spinSick.onItemSelectedListener = object : AdapterView.OnItemSelectedListener,
            AdapterView.OnItemClickListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position != 0)
                    Log.d("질병",sicklist[position])
                    sick = sicklist[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                TODO("Not yet implemented")
            }

        }
        surbinding.spinSat.onItemSelectedListener = object : AdapterView.OnItemSelectedListener,
            AdapterView.OnItemClickListener{
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.d("만족도", satilist[position])
                satisfaction = satilist[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                TODO("Not yet implemented")
            }
            }

        surbinding.btnBirth.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, myear, mmonth, mdayOfMonth ->
                var remonth: Int
                remonth = mmonth
                remonth += 1
                surbinding.tvBirth.setText(""+ myear +"/"+ remonth +"/"+ mdayOfMonth)
                syear = myear.toString()
                smonth = remonth.toString()
                sday = mdayOfMonth.toString()
                Log.d("날짜", "$syear/$smonth/$sday")
                birth = "$syear/$smonth/$sday"
            }, year, month, day)
            datePickerDialog.show()
        }
        surbinding.btnSursleep.setOnClickListener {
            val timePickerDialog = TimePickerDialog.OnTimeSetListener{ timePicker, hour, minute ->
                tim.set(Calendar.HOUR_OF_DAY, hour)
                tim.set(Calendar.MINUTE,minute)
                surbinding.tvSursleep.setText("$hour:$minute")
                Log.d("취침시간", "$hour:$minute")
                sleeptime = "$hour:$minute"
            }
            TimePickerDialog(this,timePickerDialog,tim.get(Calendar.HOUR_OF_DAY), tim.get(Calendar.MINUTE),true).show()

        }
        surbinding.btnSurwakeup.setOnClickListener {
            val timePickerDialog2 = TimePickerDialog.OnTimeSetListener{ timePicker, hour, minute ->
                tim2.set(Calendar.HOUR_OF_DAY, hour)
                tim2.set(Calendar.MINUTE,minute)
                surbinding.tvSurwakeup.setText("$hour:$minute")
                Log.d("기상시간", "$hour:$minute")
                wakeuptime = "$hour:$minute"
            }
            TimePickerDialog(this,timePickerDialog2,tim2.get(Calendar.HOUR_OF_DAY), tim2.get(Calendar.MINUTE),true).show()

        }
        var retrofit = Retrofit.Builder()
            .baseUrl("http://220.149.244.199:3001/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var surveyservice = retrofit.create(SurveyService::class.java)
        surbinding.btnSummit.setOnClickListener {
            var intent: Intent = Intent(this,UserInfoActivity::class.java)
            heigth = surbinding.etHeight.text.toString()
            weight = surbinding.etWeight.text.toString()
            surveyservice.requestSurvey(serial,gen,birth,heigth,weight,sleeptime,wakeuptime,sick,satisfaction).enqueue(object :Callback<Survey>{
                override fun onResponse(call: Call<Survey>, response: Response<Survey>) {
                    val survey = response.body()
                    Log.d("설문 성공","msg : "+survey?.msg)
                    Log.d("설문 성공","code : "+survey?.code)
                    Log.d("설문 성공","serial : "+serial)
                    Log.d("설문 성공","gender : "+gen)
                    Log.d("설문 성공","birth : "+birth)
                    Log.d("설문 성공","height : "+heigth)
                    Log.d("설문 성공","weight : "+weight)
                    Log.d("설문 성공","sleeptime : "+sleeptime)
                    Log.d("설문 성공","wakeuptime : "+wakeuptime)
                    Log.d("설문 성공","sick : "+sick)
                    Log.d("설문 성공","satisfaction : "+satisfaction)
                    startActivity(intent)
                }

                override fun onFailure(call: Call<Survey>, t: Throwable) {
                    var dialog = AlertDialog.Builder(this@SurveyActivity)
                    dialog.setTitle("에러")
                    dialog.setMessage("호출에 실패하였습니다")
                    dialog.show()
                }

            })
        }
    }
}