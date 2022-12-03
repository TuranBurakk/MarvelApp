package com.example.marvelapp.ui.ComicsFavorite

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvelapp.base.BaseFragment
import com.example.marvelapp.data.entity.UserData
import com.example.marvelapp.databinding.FragmentComicsFavoriteBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class ComicsFavoriteFragment: BaseFragment<FragmentComicsFavoriteBinding>
    (FragmentComicsFavoriteBinding::inflate){

    private val adapter by lazy { ComicsFavoriteAdapter() }
    private val database by lazy { FirebaseFirestore.getInstance()}
    private val auth by lazy { FirebaseAuth.getInstance() }
    val favList = ArrayList<UserData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.favComicsRecyclerView.adapter = adapter
        binding.favComicsRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        getData()
    }

    private fun getData(){
        database.collection(auth.currentUser!!.uid).addSnapshotListener { snapshot, exception ->
            val documents = snapshot?.documents
            favList.clear()
            if (documents != null) {
                for (document in documents){
                    val comicsName = document.get("comicsName") as? String
                    val comicsPhoto = document.get("comicsPhoto") as? String
                    val downloadComics = UserData(comicsName = comicsName, comicsPhoto = comicsPhoto)
                    if (downloadComics.comicsPhoto != null){
                        favList.add(downloadComics)
                    }
                }
                adapter.setData(favList)
            }
        }
    }
}