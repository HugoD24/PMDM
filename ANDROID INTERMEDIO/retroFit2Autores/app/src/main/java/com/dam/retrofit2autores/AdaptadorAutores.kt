package com.dam.retrofit2autores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/*Crea las filas
* Les pone el nombre del autor
* tiene un metodo changelist() para actualizar la lista*/
class AdaptadorAutores :
    RecyclerView.Adapter<AutoresViewHolder> { //usa view holder para representar cada item de la lista
    private var aut: List<Autores> //lista de datos que el adaptador va a mostrar

    constructor() {
        aut = ArrayList()
    }

    /*Crea una nueva vista cuando el recyclerView necesita mostrar un item nuevo*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutoresViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        /*Infla el layout de la fila y lo envuelve en un AutoresViewHolder*/
        return AutoresViewHolder(layoutInflater.inflate(R.layout.fila_autores, parent, false))
    }
    /*Devuelve cuantos items hay en la lista, el recyclerView usara este numero para saber cuantas
    * filas va a mostrar*/
    override fun getItemCount(): Int = aut.size

    /*Asocia los datos con la vista, se llama cada vez que una fila necesita mostrar datos*/
    override fun onBindViewHolder(holder: AutoresViewHolder, position: Int) {
        val item = aut[position] //obtiene el autor correspondiente a la posicion actual
        holder.nombre.text = item.name //muestra el nombre del autor en el textView de la fila
    }

    /*Actualiza la lista de autores con una nueva lista,
    * se llama desde MainActivity cuando llegan los datos de la API*/
    fun changelist(autores: List<Autores>) {
        aut = autores
    }

}
