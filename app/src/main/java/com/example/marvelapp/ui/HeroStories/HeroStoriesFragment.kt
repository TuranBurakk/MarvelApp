package com.example.marvelapp.ui.HeroStories

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.marvelapp.base.BaseFragment
import com.example.marvelapp.data.entity.ResultsData
import com.example.marvelapp.databinding.FragmentHeroStoriesBinding
import com.example.marvelapp.ui.TabController.TabControllerFragmentDirections
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
        val url = hero.detail?.get(0)?.url
        binding.urlTv.text = url

        binding.urlTv.setOnClickListener {
            val action = TabControllerFragmentDirections.actionTabControllerFragmentToWebViewFragment(hero)
                findNavController().navigate(action)
        }

    }

}
