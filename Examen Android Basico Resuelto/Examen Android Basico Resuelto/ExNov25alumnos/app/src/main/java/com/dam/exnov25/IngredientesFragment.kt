package com.dam.exnov25

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class IngredientesFragment() : Fragment() {
    private lateinit var rvIngrediente: RecyclerView
    private var adaptadorIngredientes= AdaptadorIngredientes()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ingredientes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvIngrediente=view.findViewById(R.id.rvIngredientes)
        rvIngrediente.layoutManager= LinearLayoutManager(context)

        rvIngrediente.addItemDecoration(
            DividerItemDecoration(
                context, LinearLayoutManager.VERTICAL
            )
        )
        rvIngrediente.adapter=adaptadorIngredientes
    }
    fun setIngredientes(ingredientes: MutableList<String>){
        adaptadorIngredientes.setIngredientes(ingredientes)
        Log.d("listas", ingredientes.toString())
    }

}