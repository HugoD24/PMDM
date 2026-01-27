package com.dam.bbddejemplo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
/*Es el punto de entrada principal para comunicar el resto de tu app con el
* esquema relacional de datos.
* Se hace una vez que se han creado las entidades y las operaciones.
* Se usa el decorador Database, las entidades que la componen, la version y los dao que se aplicaran sobre la base de datos*/
@Database(entities = [Usuario::class], version = 1, exportSchema = false)
abstract class UsuariosBBDD: RoomDatabase(){
    abstract fun usuarioDAO(): UsuarioDAO

    /*Metodo para crear la base de datos*/
    companion object DatabaseBuilder{
        private var INSTANCE : UsuariosBBDD ? = null
        /*devuelve una instacia nueva o ya existente de la base de datos*/
        fun getInstance (context: Context): UsuariosBBDD {
            if (INSTANCE == null) synchronized(Usuario::class) {
                INSTANCE = buildRoomDB(context)
            }
            return INSTANCE!!
        }

        /*Metodo para manejar la instancia de la base de datos.
        * Como parametros tiene el contexto, clase que representa la base de datos y el nombre de la misma*/
        private fun buildRoomDB (contexto : Context) =
            Room.databaseBuilder (
                contexto.applicationContext, UsuariosBBDD::class.java, "usuarios.db"
            ).build () //build crea la base de datos
    }
}
