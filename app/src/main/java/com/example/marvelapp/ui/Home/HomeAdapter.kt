package com.example.marvelapp.ui.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.data.entity.ResultsData
import com.example.marvelapp.databinding.HeroRowBinding
import com.example.marvelapp.utils.convert
import com.example.marvelapp.utils.downloadFromUrl

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.HeroHolder>() {

    class HeroHolder(val binding: HeroRowBinding): RecyclerView.ViewHolder(binding.root){

    }
    private val differCallback = object : DiffUtil.ItemCallback<ResultsData>(){
        override fun areItemsTheSame(oldItem: ResultsData, newItem: ResultsData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ResultsData, newItem: ResultsData): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroHolder {
        val view = LayoutInflater.from(parent.context)
        return HeroHolder(HeroRowBinding.inflate(view,parent,false))
    }

    override fun onBindViewHolder(holder: HeroHolder, position: Int) {

        val hero = differ.currentList[position]
        val url = "${hero.image?.path}.${hero.image?.extension}"
        val newUrl = convert(url)

        holder.binding.apply {
            nameTv.text = hero.name
            imageView.downloadFromUrl(newUrl)
        }

        holder.binding.imageView.setOnClickListener {
          val action = HomeFragmentDirections.actionHomeFragmentToTabControllerFragment(hero.id!!)
            it.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int = differ.currentList.size


}