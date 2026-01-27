package com.dam.vmappweb.bbdd

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostDAO {
    @Query("select * from PostFavoritos")
    fun selectAll(): List<PostFavoritos>

    @Insert
    fun insert(post: PostFavoritos): Long

    @Delete
    fun delete(post: PostFavoritos)

}