package com.example.marvelapp.data.remote

import com.example.marvelapp.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService : ApiService): BaseDataSource(){

    suspend fun getHero(offset : Int)= getResult {
        apiService.getHero(100,offset)
   }

    suspend fun getComics(offset : Int)= getResult {
        apiService.getComics(100,offset)
    }
}