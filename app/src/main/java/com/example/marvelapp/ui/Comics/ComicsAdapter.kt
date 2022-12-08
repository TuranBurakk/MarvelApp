package com.example.marvelapp.ui.Comics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.marvelapp.R
import com.example.marvelapp.data.entity.ComicsResults
import com.example.marvelapp.data.entity.FavComics
import com.example.marvelapp.databinding.ComicsRowBinding
import com.example.marvelapp.utils.convert
import com.example.marvelapp.utils.downloadFromUrl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class ComicsAdapter: Adapter<ComicsAdapter.ComicsHolder>(){


    private val db by lazy { FirebaseFirestore.getInstance() }
    private val auth by lazy { FirebaseAuth.getInstance() }

    class ComicsHolder(val binding : ComicsRowBinding): RecyclerView.ViewHolder(binding.root) {

    }
    private val differCallback = object : DiffUtil.ItemCallback<ComicsResults>(){
        override fun areItemsTheSame(oldItem: ComicsResults, newItem: ComicsResults): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ComicsResults, newItem: ComicsResults): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsHolder {

        val view = LayoutInflater.from(parent.context)
        return ComicsHolder(ComicsRowBinding.inflate(view,parent,false))

    }

    override fun onBindViewHolder(holder: ComicsHolder, position: Int) {

        val comic = differ.currentList[position]
        val url = "${comic.thumbnail.path}.${comic.thumbnail.extension}"
        val newUrl = convert(url)

        holder.binding.apply {
            comicsNameTv.text = comic.title
            comicsImageView.downloadFromUrl(newUrl)
        }
        holder.binding.root.setOnClickListener {
            it.findNavController()
                .navigate(ComicsFragmentDirections
                    .actionComicsFragment2ToComicsTabControllerFragment(comic))
        }
        if (comic.isFavorite){
            holder.binding.imgFav.setImageResource(R.drawable.ic_baseline_favorite_border)
        }else{
            holder.binding.imgFav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }

        holder.binding.imgFav.setOnClickListener {
            when(comic.isFavorite){
                true ->{
                    val deleteComics = FavComics(newUrl,comic.title,comic.id)
                    holder.binding.imgFav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    db.collection("user").document(auth.currentUser!!.uid)
                        .update("favComicsList",FieldValue.arrayRemove(deleteComics))
                }
                false ->{
                    val favComics = FavComics(newUrl,comic.title,comic.id)
                    holder.binding.imgFav.setImageResource(R.drawable.ic_baseline_favorite_border)
                    db.collection("user").document(auth.currentUser!!.uid).
                    update("favComicsList" ,FieldValue.arrayUnion(favComics))
                }
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

}