package com.dam.bbddejemplo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
/*Creacion de una entidad*/
@Entity(tableName = "users")
data class Usuario(
    @ColumnInfo(name = "name") var nombre:String,
    @ColumnInfo(name = "email") var correo: String
) {
    @PrimaryKey(autoGenerate = true) var id: Int =0;
}