package com.dam.exnov25

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PasosFragment : Fragment() {

    private lateinit var recycler: RecyclerView
    private val adapter = AdaptadorLista(mutableListOf<String>())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_pasos, container, false)

        recycler = view.findViewById(R.id.recyclerPasos)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())

        return view
    }

    fun setPasos(lista: MutableList<String>) {
        adapter.actualizarLista(lista)
    }
}
