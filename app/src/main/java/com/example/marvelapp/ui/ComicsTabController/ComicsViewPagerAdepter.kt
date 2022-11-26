package com.example.marvelapp.ui.ComicsTabController

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.marvelapp.data.entity.ComicsResults
import com.example.marvelapp.ui.ComicsDetail.ComicsDetailFragment
import com.example.marvelapp.ui.ComicsStoriesFragment.ComicsStoriesFragment

class ComicsViewPagerAdepter (fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle){

    private lateinit var currentArgId : ComicsResults


    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> ComicsStoriesFragment(currentArgId)
            1 -> ComicsDetailFragment(currentArgId)
            else -> Fragment()
        }
    }
    fun setArg(comic : ComicsResults){
        currentArgId = comic
    }

}