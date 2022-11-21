package com.example.marvelapp.ui.HomeFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.data.entity.ResultsData
import com.example.marvelapp.databinding.HeroRowBinding
import com.example.marvelapp.utils.convert
import com.example.marvelapp.utils.downloadFromUrl

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.HeroHolder>() {
    private var list = emptyList<ResultsData>()
    class HeroHolder(val binding: HeroRowBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroHolder {
        val view = LayoutInflater.from(parent.context)
        return HeroHolder(HeroRowBinding.inflate(view,parent,false))
    }

    override fun onBindViewHolder(holder: HeroHolder, position: Int) {
        val hero = list[position]
        holder.binding.apply {
            nameTv.text = hero.name
        }
        val url = "${hero.image?.path}.${hero.image?.extension}"
        val newUrl = convert(url)
        holder.binding.imageView.downloadFromUrl(newUrl)
    }

    override fun getItemCount(): Int = list.size

    fun updateHeroList(newList : List<ResultsData>){
        list = newList
        notifyDataSetChanged()
    }
}