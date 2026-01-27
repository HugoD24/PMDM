package com.dam.vmappweb

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dam.vmappweb.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Lógica binding
        binding = ActivityMainBinding.inflate(layoutInflater)

        //Lógica toolbar
        setSupportActionBar(binding.toolbar)


        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Logica TabLayout y ViewPager2

        var viewPagerAdapter = ViewPagerAdapter(this, 2)
        binding.viewPager2.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tablayout, binding.viewPager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Noticias"
                }

                1 -> {
                    tab.text = "Actividades"
                }
            }
        }.attach()
    }

    //Logica menú

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favoritos -> {
                val intent = Intent(this, FavoritosActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}