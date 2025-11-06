package com.dam.appmonstruo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Activity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_4)

//arraylist de objeto personas
        val listaPersona= listOf (
            Persona("Alvaro",32,"Guy"),
            Persona("Tomas",32,"Guy"),
            Persona("desi",32,"Guy"),
            Persona("jose j",32,"Guy"),
            Persona("Borja",32,"Guy"),
            Persona("Alvaro",32,"Guy"),
            Persona("tiomas",32,"Guy"),
            Persona("desi",32,"Guy"),
            Persona("Alvaro",32,"Guy")
        )


        // contructor de recyclerview
        val recycler = findViewById<RecyclerView>(R.id.recycler)// ponemos el id del reclyclerview del xml
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = Adaptador(listaPersona)// listapersona es el array

    }
}