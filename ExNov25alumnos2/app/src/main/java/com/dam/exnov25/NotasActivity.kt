package com.dam.exnov25

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class NotasActivity : AppCompatActivity() {
        private lateinit var tvNotas: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_notas)


        //bindeo
        tvNotas=findViewById(R.id.tvNotas)
        val notas=intent.getStringExtra("notas")
        tvNotas.text=notas

    }
}