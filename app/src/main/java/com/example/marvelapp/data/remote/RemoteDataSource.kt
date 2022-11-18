package com.example.marvelapp.data.remote

import com.example.marvelapp.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService : ApiService): BaseDataSource(){
   suspend fun getCharacters()= getResult {
        apiService.getCharacters()
   }
}