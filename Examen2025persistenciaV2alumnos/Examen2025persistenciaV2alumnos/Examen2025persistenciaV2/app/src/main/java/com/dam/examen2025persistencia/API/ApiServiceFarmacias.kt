package com.dam.examen2025persistencia.API


import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query
import retrofit2.http.Url

interface RegionMurciaApiService {

    @GET("api/action/datastore_search?resource_id=459b23f1-b239-49f5-a4b9-c325d66e2799")
    suspend fun getFarmacias(): FarmaciasResponse
}

object RetrofitClient {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://datosabiertos.regiondemurcia.es/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: RegionMurciaApiService = retrofit.create(RegionMurciaApiService::class.java)
}