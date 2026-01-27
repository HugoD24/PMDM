package com.dam.examen2025persistencia.bbdd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dam.examen2025persistencia.API.Farmacia


@Database(entities = [FarmaciasFavoritos::class], version = 1, exportSchema = false)
abstract class FarmaciasBBDD: RoomDatabase(){


    abstract fun swDAO(): FarmaciaDAO
    companion object DatabaseBuilder{
        private var INSTANCE : FarmaciasBBDD ? = null
        fun getInstance (context: Context): FarmaciasBBDD {
            if (INSTANCE == null) synchronized(Farmacia::class) {
                INSTANCE = buildRoomDB(context)
            }
            return INSTANCE!!
        }
        private fun buildRoomDB (contexto : Context) =
            Room.databaseBuilder (
                contexto.applicationContext, FarmaciasBBDD::class.java, "Farmacia.db"
            ).build ()
    }
}
