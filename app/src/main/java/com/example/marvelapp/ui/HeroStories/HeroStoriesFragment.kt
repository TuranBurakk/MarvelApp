package com.example.marvelapp.ui.HeroStories

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import com.example.marvelapp.base.BaseFragment
import com.example.marvelapp.data.entity.ResultsData
import com.example.marvelapp.databinding.FragmentHeroStoriesBinding
import com.example.marvelapp.ui.WebView.WebViewFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeroStoriesFragment(val hero: ResultsData) :
    BaseFragment<FragmentHeroStoriesBinding>(FragmentHeroStoriesBinding::inflate) {

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val description = "coming soon"

        if(hero.description == ""){

            binding.descriptionTv.text = description

        }else{

            binding.descriptionTv.text = hero.description

        }
        val url = hero.detail?.get(0)?.url

        val afd = SpannableStringBuilder()
        afd.append("More info: $url")
        afd.setSpan(object : ClickableSpan(){
            override fun onClick(p0: View) {

            }

            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = true
            }
        },afd.length - url?.length!!,afd.length,1)



        binding.urlTv.text =  afd

        binding.urlTv.setOnClickListener {
            startActivity(hero.detail?.get(0)?.url?.let { it1 ->
                WebViewFragment.newIntent(requireContext(),
                    it1
                )
            })
        }

    }

}
