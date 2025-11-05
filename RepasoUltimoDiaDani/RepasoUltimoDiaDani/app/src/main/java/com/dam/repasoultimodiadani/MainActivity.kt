package com.dam.repasoultimodiadani

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

public const val NPESTAÑAS = 3
class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_principal, menu)

        return true
    }


    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        R.id.action_info ->{
                //aqui haces cosas
                Log.d("repaso", "has pulsado info")
                val intent = Intent(this, NuevaActividad::class.java)
                intent.putExtra("mivalor","info desde principal")
                startActivity(intent)
            true
        }

        else -> {


            true
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //le decimos que queremos usar la toolbar
        setSupportActionBar(findViewById(R.id.toolbarPrincipal))


        //bind
        viewPager = findViewById(R.id.viewpager)
        val adapta = ViewPagerAdapter(this, NPESTAÑAS)
        viewPager.adapter = adapta
    }
}
