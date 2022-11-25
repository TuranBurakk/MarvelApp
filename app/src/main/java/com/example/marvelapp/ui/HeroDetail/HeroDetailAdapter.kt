package com.example.marvelapp.ui.HeroDetail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.data.entity.HeroComicsResultData
import com.example.marvelapp.databinding.HeroComicsRowBinding
import com.example.marvelapp.utils.convert
import com.example.marvelapp.utils.downloadFromUrl

class HeroDetailAdapter : RecyclerView.Adapter<HeroDetailAdapter.HeroComicsHolder>() {

    private var list = emptyList<HeroComicsResultData>()

    class HeroComicsHolder(val binding : HeroComicsRowBinding):RecyclerView.ViewHolder(binding.root)  {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroComicsHolder {
        val view = LayoutInflater.from(parent.context)
        return HeroComicsHolder(HeroComicsRowBinding.inflate(view,parent,false))
    }

    override fun onBindViewHolder(holder: HeroComicsHolder, position: Int) {

        val url = "${list[position].images?.path}.${list[position].images?.extension}"
        val newUrl = convert(url)

        holder.binding.apply {
           titleTv.text = list[position].title
           imageView.downloadFromUrl(newUrl)
       }
    }

    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList : List<HeroComicsResultData>){
        list = newList
        notifyDataSetChanged()
    }
}