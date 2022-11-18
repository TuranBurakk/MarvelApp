package com.example.marvelapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.marvelapp.base.BaseFragment
import com.example.marvelapp.data.entity.CharactersData
import com.example.marvelapp.data.entity.ResultsData
import com.example.marvelapp.databinding.FragmentHomeBinding
import com.example.marvelapp.ui.HomeAdapter
import com.example.marvelapp.ui.HomeFragmentViewModel
import com.example.marvelapp.utils.Resource
import com.example.marvelapp.utils.showDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel : HomeFragmentViewModel by viewModels()
    private val adapter by lazy { HomeAdapter() }


    override fun onStart() {
        super.onStart()
        showBottomBar()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.heroRecyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        getHero()
    }

    private fun getHero(){
        viewModel.getHero().observe(viewLifecycleOwner){response ->
            when(response.status){
                Resource.Status.SUCCESS ->{
                    adapter.updateHeroList(response.data?.characters?.results ?: arrayListOf())
                }
                Resource.Status.ERROR -> {
                    showDialog(requireContext(), message = "${response.message}")
                }
                Resource.Status.LOADING ->{

                }
            }

        }
    }

}