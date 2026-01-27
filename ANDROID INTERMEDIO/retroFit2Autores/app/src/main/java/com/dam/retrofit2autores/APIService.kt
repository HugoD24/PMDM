package com.dam.retrofit2autores

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url
/*Esto le dice a retrofit: "Cuando llames a getAutores, haz una peticion GET a la url que te pase"*/
interface APIService {
    @GET
    suspend fun getAutores(@Url url: String): Response<AuthorsResponse>
}
