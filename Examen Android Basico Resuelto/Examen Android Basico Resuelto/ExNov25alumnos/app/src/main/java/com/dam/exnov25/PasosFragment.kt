package com.dam.exnov25

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PasosFragment() : Fragment() {
    private lateinit var rvPasos: RecyclerView
    private var adaptadorPasos= AdaptadorPasos()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pasos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //aqui se mete la logica
        rvPasos=view.findViewById(R.id.rvPasos)
        rvPasos.layoutManager= LinearLayoutManager(context)
        rvPasos.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
        rvPasos.adapter= adaptadorPasos

    }

    fun setPasos(steps: MutableList<String>) {
        adaptadorPasos.setPasos(steps)
    }


}