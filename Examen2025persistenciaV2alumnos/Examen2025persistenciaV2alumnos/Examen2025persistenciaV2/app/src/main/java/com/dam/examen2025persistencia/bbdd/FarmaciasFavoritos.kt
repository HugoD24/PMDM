package com.dam.examen2025persistencia.bbdd

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FarmaciasFavoritos(
    @PrimaryKey(autoGenerate = true) //no funciona sin pk
    var _id: Int,
    var Nombre: String,
    var Codigo: Int,
    var Municipio: String
)
