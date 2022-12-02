package com.example.marvelapp.ui.FavoriteTabController

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.marvelapp.base.BaseFragment
import com.example.marvelapp.databinding.FragmentFavoriteTabControllerBinding
import com.google.android.material.tabs.TabLayoutMediator


class FavoriteTabControllerFragment : BaseFragment
<FragmentFavoriteTabControllerBinding>(FragmentFavoriteTabControllerBinding::inflate) {

    private val adapter by lazy { FavoriteViewPagerAdapter(childFragmentManager,lifecycle) }
    private val  fragmentArray = arrayOf("Hero","Comics")

    override fun onStart() {
        super.onStart()
        hideBottomBar()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabs()
        initBack()
    }

    private fun initTabs(){
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = fragmentArray[position]
        }.attach()
    }

    private fun initBack(){
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}