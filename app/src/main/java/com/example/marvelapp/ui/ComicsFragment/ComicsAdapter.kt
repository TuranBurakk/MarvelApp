package com.example.marvelapp.ui.ComicsFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.marvelapp.data.entity.ComicsResults
import com.example.marvelapp.databinding.ComicsRowBinding
import com.example.marvelapp.utils.convert
import com.example.marvelapp.utils.downloadFromUrl

class ComicsAdapter: Adapter<ComicsAdapter.ComicsHolder>(){

    private var comicsList = emptyList<ComicsResults>()

    class ComicsHolder(val binding : ComicsRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsHolder {

        val view = LayoutInflater.from(parent.context)
        return ComicsHolder(ComicsRowBinding.inflate(view,parent,false))

    }

    override fun onBindViewHolder(holder: ComicsHolder, position: Int) {

        val comic = comicsList[position]
        val url = "${comic.thumbnail.path}.${comic.thumbnail.extension}"
        val newUrl = convert(url)

        holder.binding.apply {
            comicsNameTv.text = comic.title
            comicsImageView.downloadFromUrl(newUrl)
        }

    }

    override fun getItemCount(): Int = comicsList.size

    fun updateComicsList(newList : List<ComicsResults>){
        comicsList = newList
        notifyDataSetChanged()
    }
}