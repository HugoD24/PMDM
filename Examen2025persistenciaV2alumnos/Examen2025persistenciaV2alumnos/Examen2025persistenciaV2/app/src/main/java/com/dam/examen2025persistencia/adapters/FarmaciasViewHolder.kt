package com.dam.examen2025persistencia.adapters

import android.text.Html
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.dam.examen2025persistencia.API.Farmacia
import com.dam.examen2025persistencia.R
import com.dam.examen2025persistencia.databinding.FilaFarmaciasBinding

class FarmaciasViewHolder(private val binding: FilaFarmaciasBinding) :
    RecyclerView.ViewHolder(binding.root) {
    init {
        binding.tbCard.inflateMenu(R.menu.menu_fav)
    }
    fun bind(personaje: Farmacia) {
        binding.tvNombre.text = personaje.Nombre
        binding.tvCodigo.text = personaje.Codigo.toString()
        binding.tvMunicipio.text = personaje.Municipio
        binding.tvID.text = personaje._id.toString()
    }
    fun getToolbar(): Toolbar {
        return binding.tbCard
    }
}