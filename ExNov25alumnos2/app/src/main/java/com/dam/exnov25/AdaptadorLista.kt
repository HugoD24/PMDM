package com.dam.exnov25

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorLista (
    private val lista: MutableList<String>
    ) : RecyclerView.Adapter<AdaptadorLista.ViewHolder>() {

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val texto: TextView = itemView.findViewById(R.id.txtItem)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fila_detalle, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.texto.text = lista[position]
        }

        override fun getItemCount(): Int = lista.size

        // ⭐ ESTE ES EL MÉTODO QUE NECESITAN TUS FRAGMENTS
        fun actualizarLista(nuevaLista: MutableList<String>) {
            lista.clear()
            lista.addAll(nuevaLista)
            notifyDataSetChanged()
        }
    }