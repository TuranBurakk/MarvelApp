package com.example.marvelapp.ui.HeroDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvelapp.base.BaseFragment
import com.example.marvelapp.data.entity.ResultsData
import com.example.marvelapp.databinding.FragmentHeroDetailBinding
import com.example.marvelapp.utils.Resource
import com.example.marvelapp.utils.showDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeroDetailFragment(val hero: ResultsData) :
    BaseFragment<FragmentHeroDetailBinding>(FragmentHeroDetailBinding::inflate) {

    private val viewModel : HeroDetailViewModel by viewModels()
    private val adapter by lazy { HeroDetailAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = hero.id
        id?.let { getHeroComics(it) }
        binding.heroComicsRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        binding.heroComicsRecyclerView.adapter = adapter
    }

    private fun getHeroComics(id:Long){
            viewModel.getCharacterComics(id).observe(viewLifecycleOwner){response ->
                when(response.status){
                    Resource.Status.SUCCESS ->{
                        response.data?.HeroComics?.HeroComicsResult.let {
                            if (it != null) {
                                adapter.setData(it)
                            }
                        }

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