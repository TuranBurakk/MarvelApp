package com.example.marvelapp.data.remote

import com.example.marvelapp.data.entity.CharactersData
import com.example.marvelapp.data.entity.Comics
import com.example.marvelapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/v1/public/characters")
    suspend fun getHero(
        @Query("limit") limit : Int ,
        @Query("offset") offset : Int,
        @Query(Constants.HEADER) header: String = Constants.HEADER_API_ID,
        @Query(Constants.HASH) hash: String = Constants.HASH_KEY,
        @Query("ts") ts: String = "1"
    ): Response<CharactersData>

    @GET("/v1/public/comics")
    suspend fun getComics(
        @Query("limit") limit : Int = 100,
        @Query("offset") offset : Int,
        @Query(Constants.HEADER) header: String = Constants.HEADER_API_ID,
        @Query(Constants.HASH) hash: String = Constants.HASH_KEY,
        @Query("ts") ts: String = "1"
    ): Response<Comics>
}