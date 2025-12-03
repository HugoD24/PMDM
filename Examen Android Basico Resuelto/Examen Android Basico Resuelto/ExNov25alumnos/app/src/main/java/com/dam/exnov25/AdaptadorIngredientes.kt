package com.dam.exnov25

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class AdaptadorIngredientes: RecyclerView.Adapter<ViewHolderIngredientes>() {
    private var ingredienteList=mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderIngredientes {
        val vista: View = LayoutInflater.from(parent.context).inflate(R.layout.fila_ingredientes, parent, false)
        val viewHolder = ViewHolderIngredientes(vista)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return this.ingredienteList.size
    }

    override fun onBindViewHolder(holder: ViewHolderIngredientes, position: Int) {
        val item=ingredienteList[position]
        holder.textViewIngrediente.text=item
        Log.d("listas", item)

    }

    fun setIngredientes(ingredientes: MutableList<String>) {
        ingredienteList=ingredientes
        Log.d("listas", ingredientes.toString())
        notifyDataSetChanged()

    }
}
