package com.example.marvelapp.ui.HeroFavorite

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvelapp.base.BaseFragment
import com.example.marvelapp.data.entity.UserData
import com.example.marvelapp.databinding.FragmentHeroFavoriteBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HeroFavoriteFragment: BaseFragment<FragmentHeroFavoriteBinding>
    (FragmentHeroFavoriteBinding::inflate) {

    private val adapter by lazy { HeroFavoriteAdapter() }
    private val database by lazy { FirebaseFirestore.getInstance()}
    private val auth by lazy { FirebaseAuth.getInstance() }
    val favList = ArrayList<UserData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        getData()

    }
    private fun getData(){
        database.collection(auth.currentUser!!.uid).addSnapshotListener { snapshot, exception ->
            val documents = snapshot?.documents
            favList.clear()
            if (documents != null) {
                for (document in documents){
                    val heroName = document.get("heroName") as? String
                    val heroPhoto = document.get("heroPhoto") as? String
                    val downloadHero = UserData(null,null,heroPhoto,heroName)
                    favList.add(downloadHero)
                }
                adapter.setData(favList)
            }
        }
    }
}