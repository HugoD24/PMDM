package com.dam.exnov25

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolderIngredientes (itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textViewIngrediente: TextView

    init {
        textViewIngrediente=itemView.findViewById(R.id.fila_ingredientes)
    }
}