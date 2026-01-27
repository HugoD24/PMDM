package com.dam.retrofit2autores

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
/*Guarda la referencia del textView de cada fila*/
class AutoresViewHolder (view: View): RecyclerView.ViewHolder(view) {
    var nombre: TextView
    init{
        nombre=view.findViewById(R.id.tvAutor)
    }
}
