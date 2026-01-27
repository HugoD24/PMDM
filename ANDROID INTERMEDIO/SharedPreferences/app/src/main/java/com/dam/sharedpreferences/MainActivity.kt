package com.dam.sharedpreferences

import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

/*DataStore es un reemplazo moderno de SharedPreferences
* usa coroutines y flow (emite automaticamente cuando los datos cambian, notifica a la UI, no depende
* del ciclo de vida de la activity, no bloquea el hilo principal)*/
class MainActivity : AppCompatActivity() {
    lateinit var nombre: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        nombre = findViewById(R.id.editTnombre)

        val viewModelSP = ViewModelProvider(this)[SharedPreferencesViewModel::class.java]
        /* Cada vez que cambie el nombre en DataStore, la UI se actualiza.*/
        lifecycleScope.launch {
            viewModelSP.getNombre(this@MainActivity).collect { name ->
                nombre.setText(name)
            }
        }
        /*Guarda el nombre*/
        viewModelSP.setNombre(this@MainActivity, "Daniel")

    }


}