package com.example.marvelapp.data.remote

import com.example.marvelapp.utils.performNetworkOperation
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    fun getHero(offset: Int) = performNetworkOperation {
        remoteDataSource.getHero(offset)
    }
    fun getComics(offset: Int) = performNetworkOperation {
        remoteDataSource.getComics(offset)
    }
    fun getHeroComics(characterId : Long) = performNetworkOperation{
        remoteDataSource.getHeroComics(characterId)
    }
}