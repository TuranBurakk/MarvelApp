package com.example.marvelapp.ui.HeroDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.marvelapp.data.entity.HeroComics
import com.example.marvelapp.data.remote.Repository
import com.example.marvelapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HeroDetailViewModel@Inject constructor(
    private val repository: Repository
) : ViewModel()  {
    fun getCharacterComics(id : Long ) : LiveData<Resource<HeroComics>>{
       return repository.getHeroComics(id)
    }
}