package com.dam.examen2025persistencia.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dam.examen2025persistencia.API.Farmacia
import com.dam.examen2025persistencia.R
import com.dam.examen2025persistencia.databinding.FilaFarmaciasBinding

class FarmaciasAdapter : RecyclerView.Adapter<FarmaciasViewHolder>() {
    private var ArrayPer = mutableListOf<Farmacia>()
    private lateinit var listener: FarmaciasAdapterCallBack

    interface FarmaciasAdapterCallBack {
        fun personajeSelected(select: Farmacia)
        fun farmaciaFAV(delete: Farmacia)
    }

    fun setOnClickPersonajeListener(listener: FarmaciasAdapterCallBack) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FarmaciasViewHolder {
        val binding =
            FilaFarmaciasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FarmaciasViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: FarmaciasViewHolder,
        position: Int
    ) {
        holder.bind(ArrayPer[position])
        holder.getToolbar().setOnMenuItemClickListener {
            when(it.itemId){
                R.id.action_fav -> {
                    listener.farmaciaFAV(ArrayPer[position])
                    true
                }
                else -> {true}
            }

        }
    }

    override fun getItemCount() = ArrayPer.size

    fun setPersonaje(farmacia: List<Farmacia>){
        this.ArrayPer = farmacia as MutableList<Farmacia>
        notifyDataSetChanged()
    }
}