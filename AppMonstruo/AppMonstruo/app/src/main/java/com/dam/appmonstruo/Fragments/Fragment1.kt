package com.dam.appmonstruo.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.dam.appmonstruo.R

class Fragment1 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflar el layout del fragment
        val view = inflater.inflate(R.layout.fragment_1, container, false)
        // Buscar el toolbar dentro del layout del fragment
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        // Configurar el toolbar como action bar de la Activity
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        // Retornar la vista inflada
        return view
    }
}