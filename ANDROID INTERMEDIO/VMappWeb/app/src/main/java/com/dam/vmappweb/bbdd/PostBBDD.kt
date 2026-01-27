package com.dam.vmappweb.bbdd

import android.content.Context
import androidx.room.*

@Database(entities = [PostFavoritos::class], version = 1, exportSchema = false)
abstract class PostBBDD: RoomDatabase(){


    abstract fun favDAO(): PostDAO
    companion object DatabaseBuilder{
        private var INSTANCE : PostBBDD ? = null
        fun getInstance (context: Context): PostBBDD {
            if (INSTANCE == null) synchronized(PostFavoritos::class) {
                INSTANCE = buildRoomDB(context)
            }
            return INSTANCE!!
        }
        private fun buildRoomDB (contexto : Context) =
            Room.databaseBuilder (
                contexto.applicationContext, PostBBDD::class.java, "posts.db"
            ).build ()
    }
}
