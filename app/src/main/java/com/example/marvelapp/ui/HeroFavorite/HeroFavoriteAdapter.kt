package com.example.marvelapp.ui.HeroFavorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.data.entity.FavHero
import com.example.marvelapp.databinding.FavHeroRowBinding
import com.example.marvelapp.utils.downloadFromUrl

class HeroFavoriteAdapter : RecyclerView.Adapter<HeroFavoriteAdapter.FavHeroHolder>() {

    private var list = emptyList<FavHero>()

    class FavHeroHolder(val binding : FavHeroRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavHeroHolder {
       val binding = FavHeroRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  FavHeroHolder(binding)
    }

    override fun onBindViewHolder(holder: FavHeroHolder, position: Int) {

        holder.binding.apply {
            nameTv.text = list[position].heroName
            imageView.downloadFromUrl(list[position].heroPhoto)
        }
    }

    override fun getItemCount() = list.size

    fun setData(newList: List<FavHero>) {
        list = newList
        notifyDataSetChanged()
    }
}