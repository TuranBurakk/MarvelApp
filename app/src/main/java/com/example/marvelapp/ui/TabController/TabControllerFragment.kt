package com.example.marvelapp.ui.TabController

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.marvelapp.base.BaseFragment
import com.example.marvelapp.databinding.FragmentTabControllerBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TabControllerFragment :
    BaseFragment<FragmentTabControllerBinding>(FragmentTabControllerBinding::inflate) {

    private val adapter by lazy { ViewPagerAdapter(childFragmentManager,lifecycle) }
    private val  fragmentArray = arrayOf("Story","Detail")
    private val args: TabControllerFragmentArgs by navArgs()

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
        adapter.setArg(args.id)
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

