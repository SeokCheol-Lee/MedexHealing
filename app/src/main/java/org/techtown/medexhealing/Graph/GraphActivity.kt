package org.techtown.medexhealing.Graph

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import org.techtown.medexhealing.HomeActivity
import org.techtown.medexhealing.MySharedPreferences
import org.techtown.medexhealing.R
import org.techtown.medexhealing.databinding.ActivityGraphBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class GraphActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val graphbinding = ActivityGraphBinding.inflate(layoutInflater)
        setContentView(graphbinding.root)
        var serial = MySharedPreferences.getUserSerial(this)

        var snore: ArrayList<Int>
        snore = arrayListOf<Int>(123,12,3124,124,213,123,124,12,31,24,12,3)
        var sleep: ArrayList<Int>
        sleep = arrayListOf<Int>(123,12,3124,124,213,123,124,12,31,24,12,3)
        var snore_chart: LineChart = findViewById(R.id.chart_snoring)
        var sleep_chart: LineChart = findViewById(R.id.chart_sleep)


        var retrofit = Retrofit.Builder()
            .baseUrl("http://220.149.244.199:3001/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val graphservice = retrofit.create(GraphService::class.java)
        graphservice.requestgraph(serial).enqueue(object : Callback<Graph>{
            override fun onResponse(call: Call<Graph>, response: Response<Graph>) {
                var req = response.body()
                Log.d("시각화","code: "+req?.code)
                Log.d("시각화","msg: "+req?.msg)
                Log.d("시각화","entem: "+req?.entem)
                Log.d("시각화","enhum: "+req?.enhum)
                Log.d("시각화","enco2: "+req?.enco2)
                Log.d("시각화","sleep: "+req?.sleep)
                Log.d("시각화","snore: "+req?.snore)
                //snore = req?.snore!!
                //sleep = req?.sleep!!
                LinchartGh(snore_chart,snore)
                LinchartGh(sleep_chart,sleep)
                graphbinding.tvEntm.setText("${req?.entem}°C")
                graphbinding.tvEnhum.setText("${req?.enhum}%")
                graphbinding.tvEnco2.setText("${req?.enco2}ppm")
            }

            override fun onFailure(call: Call<Graph>, t: Throwable) {
                Log.d("에러 메시지","err :"+t.localizedMessage)
            }

        })
        graphbinding.btnBack.setOnClickListener {
            val intent: Intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }


    }
    private fun LinchartGh(lineChart: LineChart, list: ArrayList<Int>){
        val entries = ArrayList<Entry>()
        for(i in 0 until list.size){
            entries.add(Entry(i.toFloat(),list[i].toFloat()))
        }
        val dataset = LineDataSet(entries,"")
        lineChart.getTransformer(YAxis.AxisDependency.LEFT)
        lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        val data = LineData(dataset)

        lineChart.data = data
        lineChart.invalidate()
    }
}