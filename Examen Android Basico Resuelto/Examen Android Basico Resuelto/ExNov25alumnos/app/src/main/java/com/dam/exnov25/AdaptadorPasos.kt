package com.dam.exnov25

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdaptadorPasos: RecyclerView.Adapter<ViewHolderPasos>() {
    private var listaPasos=mutableListOf<String>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderPasos {
        val vista: View = LayoutInflater.from(parent.context).inflate(R.layout.fila_pasos, parent, false)
        val viewHolder= ViewHolderPasos(vista)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolderPasos, position: Int) {
        val itemPasos=listaPasos[position]
        holder.tvPasos.text=itemPasos
    }

    override fun getItemCount(): Int {
        return listaPasos.size
    }

    fun setPasos(steps: MutableList<String>) {
        listaPasos=steps
        notifyDataSetChanged()
    }

}
