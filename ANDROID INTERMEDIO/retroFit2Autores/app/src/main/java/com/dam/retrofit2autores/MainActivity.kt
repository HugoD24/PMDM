package com.dam.retrofit2autores


import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var recy: RecyclerView
    val adaptadorRecyclerView = AdaptadorAutores()
    private lateinit var buscador: SearchView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        //bind
        buscador = findViewById(R.id.svAutores)
        recy = findViewById(R.id.rvAutores)
        recy.layoutManager = LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.VERTICAL, false
        )
        recy.adapter = adaptadorRecyclerView
        buscador.setOnQueryTextListener(this)

    }


    /*Esto configura retrofit(libreria encargada del consumo de las API) con la url base y el convertidor de JSON*/
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://openlibrary.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun busquedaAutores(query: String) {
        lifecycleScope.launch(Dispatchers.IO) { //Lanza una coroutine en segundo plano
            val call = getRetrofit().create(APIService::class.java) //llama a la API con Retrofit
                .getAutores("/search/authors.json?q=$query")
            val autoresAPI = call.body()
            if (call.isSuccessful) { //si la respuesta es correcta
                val autores = autoresAPI?.autores ?: emptyList() //extrae la lista de autores
                withContext(Dispatchers.Main) { //cambia al hilo principal para actualizar el recyclerView
                    adaptadorRecyclerView.changelist(autores) //cambia la lista del adaptador
                    adaptadorRecyclerView.notifyDataSetChanged()
                }
            } else { //si falla muestra un toast con el error
                Toast.makeText(applicationContext, "error API", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            busquedaAutores(query.lowercase())
        }
        //obtiene el servicio del sistema que controla el reclado virtual
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        //oculta el teclado despues de enviar la busqueda
        imm.hideSoftInputFromWindow(this.buscador.windowToken, 0)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true //no hacemos nada mientras escribe
    }
}
