package com.example.marvelapp.ui.FavoriteTabController

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.marvelapp.ui.ComicsFavorite.ComicsFavoriteFragment
import com.example.marvelapp.ui.HeroFavorite.HeroFavoriteFragment

class FavoriteViewPagerAdapter (fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle){



    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> HeroFavoriteFragment()
            1 -> ComicsFavoriteFragment()
            else -> Fragment()
        }
    }

    }