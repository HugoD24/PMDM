package com.dam.examen2025persistencia.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dam.examen2025persistencia.R
import com.dam.examen2025persistencia.adapters.FavoritosAdapter
import com.dam.examen2025persistencia.bbdd.FarmaciasBBDD
import com.dam.examen2025persistencia.bbdd.FarmaciasFavoritos
import com.dam.examen2025persistencia.databinding.ActivityFavoritosBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritosBinding
    private lateinit var adapter: FavoritosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityFavoritosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.rvFavoritoss.layoutManager = LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.VERTICAL, false
        )
        adapter = FavoritosAdapter()
        binding.rvFavoritoss.adapter = adapter
        loadFavoritos()
    }
    /**
     * Este metodo se utiliza para cargar los personajes de la BBDD. Te permite reutilziarlo.
     */
    fun loadFavoritos(){
        lifecycleScope.launch(Dispatchers.IO) {
            val database = FarmaciasBBDD.DatabaseBuilder.getInstance(applicationContext)
            val favs = database.swDAO().selectAll()
            withContext(Dispatchers.Main) {
                adapter.setFav(favs)
            }
        }

        adapter.setOnClickFarmaciaListener(object: FavoritosAdapter.FarmaciasAdapterCallBack{
            override fun farmaciaSelected(select: FarmaciasFavoritos) {
                TODO("Not yet implemented")
            }

            override fun farmaciaDelete(delete: FarmaciasFavoritos) { //no olvidar cambiar a base de datos
                lifecycleScope.launch(Dispatchers.IO) {
                    val database= FarmaciasBBDD.DatabaseBuilder.getInstance(applicationContext)
                    try{
                        database.swDAO().delete(delete)
                        var favs=database.swDAO().selectAll()
                        withContext(Dispatchers.Main){
                            adapter.setFav(favs)
                        }
                    }catch (e: Exception){
                    }
                }
            }

        })
    }
}