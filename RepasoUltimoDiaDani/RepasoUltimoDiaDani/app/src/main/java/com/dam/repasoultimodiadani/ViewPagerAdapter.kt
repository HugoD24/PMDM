package com.dam.repasoultimodiadani

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter (activity: AppCompatActivity, var itemsCount: Int) :
    FragmentStateAdapter(activity) {
    private val howToPMDM= PMDMFragment()

    private val otroFragment = OtroFragmento()

    override fun getItemCount(): Int {
        return itemsCount
    }

    override fun createFragment(position: Int): Fragment {

        return when(position) {
            0-> howToPMDM
            1-> otroFragment
            else -> PMDMFragment()
        }
    }
}
