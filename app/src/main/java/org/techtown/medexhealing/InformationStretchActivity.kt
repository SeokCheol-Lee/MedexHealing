package org.techtown.medexhealing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.techtown.medexhealing.Information.ApiInterface
import org.techtown.medexhealing.Information.MyAdapter
import org.techtown.medexhealing.Information.MyDataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InformationStretchActivity : AppCompatActivity() {

    lateinit var myAdapter: MyAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information_stretch)

        val recyclerview_sleepContent = findViewById<RecyclerView>(R.id.recyclerview_sleepContent)

        recyclerview_sleepContent.setHasFixedSize(true)

        linearLayoutManager = LinearLayoutManager(this)
        recyclerview_sleepContent.layoutManager = linearLayoutManager



        fun getMyData() {
            val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(ApiInterface::class.java)

            val retrofitData = retrofitBuilder.getStretchData()

            retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
                override fun onResponse(call: Call<List<MyDataItem>?>, response: Response<List<MyDataItem>?>) {
                    Log.d("retrofit", response?.body().toString())
                    val responseBody = response.body()!!

                    myAdapter = MyAdapter(baseContext, responseBody)
                    myAdapter.notifyDataSetChanged()
                    recyclerview_sleepContent.adapter = myAdapter


                }

                override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                    Log.d("MainActivity", "onFailure: " + t.message)
                }

            })
        }

        getMyData()
    }
}