package com.example.marvelapp.data.remote

import com.example.marvelapp.utils.performNetworkOperation
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    fun getHero() = performNetworkOperation {
        remoteDataSource.getCharacters()
    }
}