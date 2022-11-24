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
       holder.binding.apply {
           titleTv.text = list[position].title
           val url = "${list[position].images?.get(0)?.path}" +
                   ".${list[position].images?.get(0)?.extension}"
           val newUrl = convert(url)
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