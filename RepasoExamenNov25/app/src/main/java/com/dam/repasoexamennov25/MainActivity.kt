package com.dam.repasoexamennov25

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2

const val NPESTAÑAS = 3

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2


    //toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_info ->{
            //aqui haria cosas
            Log.d("repaso", "Has pulsado Info")
            val intent= Intent(this, Nueva2aCTIVIDAD::class.java)
            intent.putExtra("mivalor", "info desde principal")
            startActivity(intent)
            true
        }
        else ->{
            true
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //le decimos que queremos utilizar el componente toolbar
        setSupportActionBar(findViewById(R.id.toolbarPrincipal))

        //bind
        viewPager = findViewById(R.id.viewpager)
        val adapta = ViewPagerAdapter(this, NPESTAÑAS)
        viewPager.adapter = adapta
    }
}