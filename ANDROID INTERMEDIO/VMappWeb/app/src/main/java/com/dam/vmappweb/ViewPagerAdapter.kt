package com.dam.vmappweb

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(activity: AppCompatActivity, var itemsCount: Int) : FragmentStateAdapter(activity) {

    private val noticiasFragment= NoticiasFragment()

    private val actividadesFragment= ActividadesFragment()

    override fun getItemCount(): Int {
        return itemsCount
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0-> noticiasFragment
            else -> actividadesFragment

        }
    }


}

