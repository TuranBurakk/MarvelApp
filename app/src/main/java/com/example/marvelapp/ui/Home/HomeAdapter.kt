package com.example.marvelapp.ui.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.R
import com.example.marvelapp.data.entity.ResultsData
import com.example.marvelapp.data.entity.UserData
import com.example.marvelapp.databinding.HeroRowBinding
import com.example.marvelapp.utils.convert
import com.example.marvelapp.utils.downloadFromUrl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomeAdapter(): RecyclerView.Adapter<HomeAdapter.HeroHolder>() {

    private val db by lazy { FirebaseFirestore.getInstance() }
    private val auth by lazy { FirebaseAuth.getInstance() }


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
        val heroName = hero.name.toString()
        holder.binding.apply {
            nameTv.text = hero.name
            imageView.downloadFromUrl(newUrl)
        }

        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToTabControllerFragment(hero!!)
            it.findNavController().navigate(action)
        }
        if (hero.isFavorite){
            holder.binding.imgFav.setImageResource(R.drawable.ic_baseline_favorite_border)
        }else{
            holder.binding.imgFav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
        holder.binding.imgFav.setOnClickListener {
            when(hero.isFavorite){
                true ->{
                    holder.binding.imgFav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    db.collection(auth.currentUser!!.uid).document(hero.id.toString()).delete()
                }
                false ->{
                    holder.binding.imgFav.setImageResource(R.drawable.ic_baseline_favorite_border)
                    db.collection(auth.currentUser!!.uid).document(hero.id.toString()).set(UserData(null,null,newUrl,heroName))
            }
            }
            }

    }

    override fun getItemCount(): Int = differ.currentList.size


}