package com.example.catsapi.data

import com.example.catsapi.model.CatFacts
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/fact")
    suspend fun getRandomFact() : Response<CatFacts>
}