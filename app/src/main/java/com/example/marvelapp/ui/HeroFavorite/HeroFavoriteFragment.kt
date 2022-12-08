package com.example.marvelapp.ui.HeroFavorite

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvelapp.base.BaseFragment
import com.example.marvelapp.data.entity.FavHero
import com.example.marvelapp.data.entity.UserData
import com.example.marvelapp.databinding.FragmentHeroFavoriteBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class HeroFavoriteFragment: BaseFragment<FragmentHeroFavoriteBinding>
    (FragmentHeroFavoriteBinding::inflate) {

    private val adapter by lazy { HeroFavoriteAdapter() }
    private val database by lazy { FirebaseFirestore.getInstance()}
    private val auth by lazy { FirebaseAuth.getInstance() }
    private val favList = ArrayList<FavHero>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        getData()

    }
    private fun getData() {
        database.collection("user").document(auth.currentUser!!.uid).get().addOnSuccessListener {
            val hero = it.toObject<UserData>()
            if (hero != null) {
                for (item in hero.favHeroList){
                    val name = item.heroName
                    val photo = item.heroPhoto
                    val downloadHero = FavHero(photo,name)
                    favList.add(downloadHero)
                }
                adapter.setData(favList)
            }
        }
    }
}