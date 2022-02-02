package org.techtown.medexhealing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.techtown.medexhealing.databinding.ActivityModSelectBinding

class ModSelectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val msbinding = ActivityModSelectBinding.inflate(layoutInflater)
        setContentView(msbinding.root)

        val list = listOf(Modfragment1(),Modfragment2(),Modfragment3(),Modfragment4())

        val pagerAdapter = FragmentPagerAdapter(list, this)

        msbinding.viewPager.adapter = pagerAdapter

        val titles = listOf("숙면모드","휴식모드","안전모드","사용자모드")

        TabLayoutMediator(msbinding.tabLayout, msbinding.viewPager){tab, position ->
            tab.text = titles.get(position)
        }.attach()
        msbinding.tabLayout.getTabAt(0)?.setIcon(R.drawable.sleeping)
        msbinding.tabLayout.getTabAt(1)?.setIcon(R.drawable.rebreak)
        msbinding.tabLayout.getTabAt(2)?.setIcon(R.drawable.shield)
        msbinding.tabLayout.getTabAt(3)?.setIcon(R.drawable.settings)
    }
}

class FragmentPagerAdapter(val fragmentList: List<Fragment>,fragmentActivity: FragmentActivity)
    : FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList.get(position)
    }

}