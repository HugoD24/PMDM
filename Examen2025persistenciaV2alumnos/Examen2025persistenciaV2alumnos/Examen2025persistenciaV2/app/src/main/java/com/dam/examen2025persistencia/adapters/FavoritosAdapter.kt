package com.dam.examen2025persistencia.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dam.examen2025persistencia.R
import com.dam.examen2025persistencia.bbdd.FarmaciasFavoritos
import com.dam.examen2025persistencia.databinding.FilaFarmaciasBinding

class FavoritosAdapter : RecyclerView.Adapter<FavoritosViewHolder>() {
    private var ArrayPer = mutableListOf<FarmaciasFavoritos>() //cambiar a base de datos
    private lateinit var listener: FarmaciasAdapterCallBack

    interface FarmaciasAdapterCallBack {
        fun farmaciaSelected(select: FarmaciasFavoritos)
        fun farmaciaDelete(delete: FarmaciasFavoritos) //no olvidar cambiar a base de datos
    }

    fun setOnClickFarmaciaListener(listener: FarmaciasAdapterCallBack) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoritosViewHolder {
        val binding =
            FilaFarmaciasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritosViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: FavoritosViewHolder,
        position: Int
    ) {
        holder.bind(ArrayPer[position])
        holder.getToolbar().setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_eliminar -> {
                    listener.farmaciaDelete(ArrayPer[position])
                    true
                }

                else -> {
                    true
                }
            }

        }
    }

    override fun getItemCount() = ArrayPer.size

    fun setFav(personajes: List<FarmaciasFavoritos>) {
        this.ArrayPer = personajes as MutableList<FarmaciasFavoritos>
        notifyDataSetChanged()
    }
}