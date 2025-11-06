package com.dam.appmonstruo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dam.appmonstruo.Fragments.Fragment1
import com.dam.appmonstruo.Fragments.Fragment2
import com.dam.appmonstruo.Fragments.Fragment3


//adaptador del viewpage
class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 3 // Cuántos fragments hay
    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> Fragment1()
            1 -> Fragment2()
            else -> Fragment3()
        }
    }
}