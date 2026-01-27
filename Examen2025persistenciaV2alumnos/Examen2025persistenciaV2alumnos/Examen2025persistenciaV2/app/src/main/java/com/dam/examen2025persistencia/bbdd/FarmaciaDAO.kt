package com.dam.examen2025persistencia.bbdd

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FarmaciaDAO{
    @Query("select * from FarmaciasFavoritos")
    fun selectAll(): List<FarmaciasFavoritos>

    @Insert
    fun insert(per: FarmaciasFavoritos): Long

    @Delete
    fun delete(per: FarmaciasFavoritos)
}
