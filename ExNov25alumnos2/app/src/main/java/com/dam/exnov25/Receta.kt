package com.dam.exnov25

import java.io.Serializable

class Receta(
    val id: Int,
    val name: String,
    val time: Int,
    val servings: Int,
    val image: String,
    val ingredients:  MutableList<String>,
    val steps:  MutableList<String>,
    val notes: String
): Serializable