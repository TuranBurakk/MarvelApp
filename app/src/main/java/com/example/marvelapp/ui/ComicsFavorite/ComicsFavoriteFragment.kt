package com.example.marvelapp.ui.ComicsFavorite

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvelapp.base.BaseFragment
import com.example.marvelapp.data.entity.UserData
import com.example.marvelapp.databinding.FragmentComicsFavoriteBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject


class ComicsFavoriteFragment: BaseFragment<FragmentComicsFavoriteBinding>
    (FragmentComicsFavoriteBinding::inflate){

    private val adapter by lazy { ComicsFavoriteAdapter() }
    private val database by lazy { FirebaseFirestore.getInstance()}
    private val auth by lazy { FirebaseAuth.getInstance() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.favComicsRecyclerView.adapter = adapter
        binding.favComicsRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        getData()
    }

    private fun getData(){
        database.collection("user").document(auth.currentUser!!.uid).get().addOnSuccessListener {
            val comics = it.toObject<UserData>()
            if (comics != null){
                adapter.setData(comics.favComicsList)
            }
        }
    }
}