package com.example.marvelapp.ui.HeroStories

import android.os.Bundle
import android.view.View
import com.example.marvelapp.base.BaseFragment
import com.example.marvelapp.data.entity.ResultsData
import com.example.marvelapp.databinding.FragmentHeroStoriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeroStoriesFragment(val hero: ResultsData) : BaseFragment<FragmentHeroStoriesBinding>(FragmentHeroStoriesBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val description = "coming soon"
        if(hero.description == ""){
            binding.descriptionTv.text = description
        }else{
            binding.descriptionTv.text = hero.description
        }



    }

}
