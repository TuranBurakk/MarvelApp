package com.example.marvelapp.ui.ComicsFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.marvelapp.data.entity.Comics
import com.example.marvelapp.data.remote.Repository
import com.example.marvelapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComicsFragmentViewModel @Inject constructor(private val repository : Repository) : ViewModel() {

    fun getComics(offset : Int): LiveData<Resource<Comics>>{
       return repository.getComics(offset)
    }

}