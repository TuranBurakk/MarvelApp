package com.example.marvelapp.ui.ComicsTabController

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.marvelapp.base.BaseFragment
import com.example.marvelapp.databinding.FragmentComicsTabControllerBinding
import com.google.android.material.tabs.TabLayoutMediator


class ComicsTabControllerFragment :
    BaseFragment<FragmentComicsTabControllerBinding>(FragmentComicsTabControllerBinding::inflate) {

    private val adapter by lazy { ComicsViewPagerAdepter(childFragmentManager,lifecycle) }
    private val fragmentArray = arrayOf("Story","Detail")
    private val args : ComicsTabControllerFragmentArgs by navArgs()


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
        args.comics.let { adapter.setArg(it) }
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = fragmentArray[position]
        }.attach()
    }

    private fun initBack(){
        binding.btnBack.setOnClickListener {
            findNavController().navigate(ComicsTabControllerFragmentDirections.actionComicsTabControllerFragmentToComicsFragment2())
        }
    }
}