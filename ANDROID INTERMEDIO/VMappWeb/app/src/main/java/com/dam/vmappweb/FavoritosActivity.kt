package com.dam.vmappweb

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dam.vmappweb.bbdd.PostBBDD
import com.dam.vmappweb.bbdd.PostFavoritos
import com.dam.vmappweb.databinding.ActivityFavoritosBinding
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

        binding.rvFav.layoutManager = LinearLayoutManager(applicationContext)
        adapter = FavoritosAdapter()
        binding.rvFav.adapter = adapter

        lifecycleScope.launch(Dispatchers.IO) {
            val database = PostBBDD.DatabaseBuilder.getInstance(applicationContext)
            val favs = database.favDAO().selectAll()
            withContext(Dispatchers.Main) {
                adapter.setPosts(favs)
            }
        }

        adapter.setOnClickPostListener(object: FavoritosAdapter.PostAdapterCallback{
            override fun postSelected(city: PostFavoritos) {
                TODO("Not yet implemented")
            }

            override fun postDelete(post: PostFavoritos) {
                lifecycleScope.launch(Dispatchers.IO) {
                    val database= PostBBDD.DatabaseBuilder.getInstance(applicationContext)
                    try{
                        database.favDAO().delete(post)
                        var favs=database.favDAO().selectAll()
                        withContext(Dispatchers.Main){
                            adapter.setPosts(favs)
                        }
                    }catch (e: Exception){
                    }
                }
            }
        })
    }
}

