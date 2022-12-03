package com.example.marvelapp.ui.ComicsFavorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.data.entity.UserData
import com.example.marvelapp.databinding.FavComicsRowBinding
import com.example.marvelapp.utils.downloadFromUrl

class ComicsFavoriteAdapter : RecyclerView.Adapter<ComicsFavoriteAdapter.FavComicsHolder>() {

    private var list = emptyList<UserData>()

    class FavComicsHolder(val binding : FavComicsRowBinding ) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavComicsHolder {
       val binding = FavComicsRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavComicsHolder(binding)
    }

    override fun onBindViewHolder(holder: FavComicsHolder, position: Int) {
        holder.binding.apply {
            nameTv.text = list[position].comicsName
            imageView.downloadFromUrl(list[position].comicsPhoto)
        }
    }

    override fun getItemCount() = list.size

    fun setData(newList: List<UserData>) {
        list = newList
        notifyDataSetChanged()
    }

}