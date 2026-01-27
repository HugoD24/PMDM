package com.dam.bbddejemplo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
/*Representa las acciones que se pueden hacer sobre las tablas de la base de datos*/
@Dao
interface UsuarioDAO {
    @Query("Select * from users")
    fun selectAll(): List<Usuario>

    @Query("Select * from users Where name = :name")
    fun getByName(name: String): List<Usuario>

    @Query("Delete from users Where name = :name")
    fun deleteByName(name: String)

    @Query("SELECT * FROM users WHERE email = :correo LIMIT 1")
    fun getByEmail(correo: String): Usuario?

    @Query("Update users set email= :newMail WHERE email= :oldMail")
    fun updateByEmail(oldMail: String, newMail: String)

    @Insert
    fun insert(usuario: Usuario): Long

    @Delete
    fun delete(usuario: Usuario)
}
