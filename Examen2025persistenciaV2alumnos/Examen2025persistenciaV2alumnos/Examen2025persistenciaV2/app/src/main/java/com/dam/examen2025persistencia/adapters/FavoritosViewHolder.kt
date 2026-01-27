package com.dam.examen2025persistencia.adapters

import android.text.Html
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.dam.examen2025persistencia.R
import com.dam.examen2025persistencia.bbdd.FarmaciasFavoritos
import com.dam.examen2025persistencia.databinding.FilaFarmaciasBinding

class FavoritosViewHolder (private val binding: FilaFarmaciasBinding) :
    RecyclerView.ViewHolder(binding.root) {
    init {
        binding.tbCard.inflateMenu(R.menu.menu_eliminar)
    }

    fun bind(personaje: FarmaciasFavoritos) { //no olvidar cambiar a base de datos
        binding.tvNombre.text = personaje.Nombre
        binding.tvCodigo.text = personaje.Codigo.toString()
        binding.tvMunicipio.text = personaje.Municipio
        binding.tvID.text = personaje._id.toString()
    }

    fun getToolbar(): Toolbar {
        return binding.tbCard
    }
}