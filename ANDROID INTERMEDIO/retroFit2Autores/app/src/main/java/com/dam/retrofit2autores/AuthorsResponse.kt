package com.dam.retrofit2autores

import com.google.gson.annotations.SerializedName
/*Representa la respuesta completa de la API. Solo nos interesa el campo docs que es una lista de autores*/
class AuthorsResponse (var numFound: Int, @SerializedName("docs") var autores: List<Autores>) {

}