package org.techtown.medexhealing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class InformationSleepActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information_sleep)

        val sleepInfoList = mutableListOf<String>()

        // 아래 항목은 예시 항목으로 나중에 바꿔야함!!!!
        sleepInfoList.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus eget pharetra diam.")
        sleepInfoList.add("Curabitur pellentesque ipsum bibendum orci rhoncus, id auctor mi posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris sit amet venenatis elit, non iaculis felis.")
        sleepInfoList.add("Donec ac sapien malesuada, fringilla arcu et, tincidunt nisl. Etiam efficitur lacinia imperdiet. Maecenas massa enim, dictum id lectus ac, ullamcorper mattis justo")
        sleepInfoList.add("Proin in dictum libero, non mattis tortor. Vivamus quis magna ut est facilisis sodales sit amet et augue.")
        sleepInfoList.add("Aliquam consectetur orci ante, sit amet ullamcorper mauris laoreet eget.")


        val sleepInfoListView = findViewById<ListView>(R.id.sleepInfoList)
        val adapter = InformationSleepAdapter(sleepInfoList)

        sleepInfoListView.adapter = adapter
    }
}