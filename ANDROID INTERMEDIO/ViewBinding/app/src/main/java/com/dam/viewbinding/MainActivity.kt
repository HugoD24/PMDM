package com.dam.viewbinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dam.viewbinding.databinding.ActivityMainBinding   // IMPORTANTE

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding   // Representa el layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inflar el binding
        binding = ActivityMainBinding.inflate(layoutInflater)

        // 2. Establecer la vista raíz como contenido
        setContentView(binding.root)

        // 3. Usar las vistas directamente
        binding.textView.text = "Hola, esto es View Binding"
    }
}