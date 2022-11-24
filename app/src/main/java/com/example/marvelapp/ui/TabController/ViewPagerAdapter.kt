package com.example.marvelapp.ui.TabController

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.marvelapp.data.entity.ResultsData
import com.example.marvelapp.ui.HeroDetail.HeroDetailFragment
import com.example.marvelapp.ui.HeroStories.HeroStoriesFragment

class ViewPagerAdapter(fragmentManager: FragmentManager,lifecycle: Lifecycle) :
  FragmentStateAdapter(fragmentManager, lifecycle){

   private lateinit var currentArgId : ResultsData


    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> HeroStoriesFragment(currentArgId)
            1 -> HeroDetailFragment(currentArgId)
            else -> Fragment()
        }
    }
    fun setArg(hero : ResultsData){
        currentArgId = hero
    }

}