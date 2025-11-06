package com.dam.appmonstruo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2

class Activity5 : AppCompatActivity() {

    private lateinit  var viewPage: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_5)
        //bindeo
        viewPage = findViewById(R.id.viewpage)
        val adapter = ViewPagerAdapter(this)
        viewPage.adapter = adapter




    }
}