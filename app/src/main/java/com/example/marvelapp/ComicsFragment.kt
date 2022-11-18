package com.example.marvelapp

import com.example.marvelapp.base.BaseFragment
import com.example.marvelapp.databinding.FragmentComicsBinding


class ComicsFragment : BaseFragment<FragmentComicsBinding>(FragmentComicsBinding::inflate) {
    override fun onStart() {
        super.onStart()
        showBottomBar()
    }
}