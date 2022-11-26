package com.example.marvelapp.ui.ComicsStoriesFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.marvelapp.base.BaseFragment
import com.example.marvelapp.data.entity.ComicsResults
import com.example.marvelapp.databinding.FragmentComicsStoriesBinding
import com.example.marvelapp.databinding.FragmentHeroStoriesBinding
import com.example.marvelapp.ui.WebView.WebViewFragment


class ComicsStoriesFragment(val comics: ComicsResults) :
    BaseFragment<FragmentComicsStoriesBinding>(FragmentComicsStoriesBinding::inflate) {

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val description = "coming soon"

        if(comics.description == ""){

            binding.descriptionTv.text = description

        }else{

            binding.descriptionTv.text = comics.description

        }

        val url = comics.comicsDetails?.get(0)?.detail

        binding.urlTv.text = "More info : $url"

        binding.urlTv.setOnClickListener {
            startActivity(comics.comicsDetails?.get(0)?.detail?.let { it1 ->
                WebViewFragment.newIntent(requireContext(),
                    it1
                )
            })
        }
    }

}