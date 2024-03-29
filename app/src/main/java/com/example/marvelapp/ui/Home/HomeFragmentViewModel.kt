package com.example.marvelapp.ui.Home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.marvelapp.data.entity.CharactersData
import com.example.marvelapp.data.remote.Repository
import com.example.marvelapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val repository: Repository) : ViewModel() {


    fun getHero(offset : Int): LiveData<Resource<CharactersData>>{
            return repository.getHero(offset)
        }


}