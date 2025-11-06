package com.dam.appmonstruo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


//ListaPersonas es el array que se crea en el activity4
class Adaptador(private val listaPersonas: List<Persona>) :
    RecyclerView.Adapter<Adaptador.PersonaViewHolder>() {



// tienes que poner mismo nombre a la clase que en la linea 12 (PersonaViewHolder)
    class PersonaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            //bindeamos con los id de lo que vamos a mostrar del xml de fila_persona
        val nombre: TextView = itemView.findViewById(R.id.nombre)
        val apellido: TextView = itemView.findViewById(R.id.apellido)
        val edad: TextView = itemView.findViewById(R.id.edad)
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.fila_persona, parent, false)
        return PersonaViewHolder(vista)
    }

    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {


        val persona = listaPersonas[position]

        //esto es como una especia de bindeo, persona.nombre ( es como un geter)
        holder.nombre.text = persona.nombre
        holder.apellido.text = persona.apellido
        holder.edad.text = "Edad: ${persona.edad}"
    }

    override fun getItemCount(): Int = listaPersonas.size
}


