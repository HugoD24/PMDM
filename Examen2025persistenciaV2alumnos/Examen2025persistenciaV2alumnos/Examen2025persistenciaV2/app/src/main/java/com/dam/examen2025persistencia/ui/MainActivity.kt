package com.dam.examen2025persistencia.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dam.examen2025persistencia.API.Farmacia
import com.dam.examen2025persistencia.API.RegionMurciaApiService
import com.dam.examen2025persistencia.API.RetrofitClient
import com.dam.examen2025persistencia.R
import com.dam.examen2025persistencia.adapters.FarmaciasAdapter
import com.dam.examen2025persistencia.bbdd.FarmaciasBBDD
import com.dam.examen2025persistencia.bbdd.FarmaciasFavoritos
import com.dam.examen2025persistencia.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: FarmaciasAdapter

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)

        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.rvPersonajes.layoutManager = LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.VERTICAL, false
        )
        adapter = FarmaciasAdapter()
        binding.rvPersonajes.adapter = adapter

        //no olvidar inicializar listener
        adapter.setOnClickPersonajeListener(object : FarmaciasAdapter.FarmaciasAdapterCallBack {
            override fun personajeSelected(select: Farmacia) {
            }

            override fun farmaciaFAV(farmacia: Farmacia) {
                lifecycleScope.launch(Dispatchers.IO) {
                    val db = FarmaciasBBDD.getInstance(applicationContext)
                    db.swDAO().insert(
                        FarmaciasFavoritos(
                            _id = farmacia._id,
                            Nombre = farmacia.Nombre,
                            Codigo = farmacia.Codigo,
                            Municipio = farmacia.Municipio
                        )
                    )
                }
            }
        })
        fetchPersonajes()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://swapi.py4e.com/api/").addConverterFactory(
            GsonConverterFactory.create()
        ).build()
    }

    /**
     * Este metodo se utiliza para cargar las farmacias al iniciar la app. Te permite reutilziarlo.
     */

    private fun fetchPersonajes() {
        lifecycleScope.launch(Dispatchers.IO) {
            val call = RetrofitClient.retrofit.create(RegionMurciaApiService::class.java).getFarmacias()
            val farmaciasAPI = call.result.records
            Log.d("dani", farmaciasAPI.toString())
//            if (call.success){
//                //val farmacias = farmaciasAPI?.?:emptyList()
                withContext(Dispatchers.Main){
                    adapter.setPersonaje(farmaciasAPI)
                }
//            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favoritos -> {
                val intent = Intent(this, FavoritosActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}