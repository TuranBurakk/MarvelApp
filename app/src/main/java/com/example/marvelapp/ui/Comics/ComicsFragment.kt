package com.example.marvelapp.ui.Comics

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.base.BaseFragment
import com.example.marvelapp.data.entity.ComicsResults
import com.example.marvelapp.databinding.FragmentComicsBinding
import com.example.marvelapp.utils.Constants
import com.example.marvelapp.utils.Resource
import com.example.marvelapp.utils.showDialog
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ComicsFragment : BaseFragment<FragmentComicsBinding>(FragmentComicsBinding::inflate) {

    private val adapter by lazy { ComicsAdapter() }
    private val comicsList = arrayListOf<ComicsResults>()
    private val viewModel : ComicsFragmentViewModel by viewModels()
    private var totalCount = 0
    private var offset = Constants.offset

    override fun onStart() {
        super.onStart()
        showBottomBar()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.comicsRecyclerView.adapter = adapter
        binding.comicsRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        getComics(offset)
        onScrollListener()
        searchViewListener()
    }

    private fun getComics(offset : Int){

        viewModel.getComics(offset).observe(viewLifecycleOwner){response ->

            when(response.status){

                Resource.Status.SUCCESS ->{
                    totalCount = response.data?.comics?.total ?: 0
                    comicsList.addAll(response.data?.comics?.results ?: arrayListOf())
                    val scrollDistance = comicsList.size - (response.data?.comics?.results?.size ?: 0)
                    binding.comicsRecyclerView.scrollToPosition(scrollDistance)
                    setData()
                }

                Resource.Status.ERROR -> {
                    showDialog(requireContext(), message = "${response.message}")
                }

                Resource.Status.LOADING ->{

                }
            }
        }
    }

    private fun onScrollListener(){
        binding.comicsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (
                    !binding.comicsRecyclerView.canScrollVertically(1)&&
                    newState == RecyclerView.SCROLL_STATE_IDLE &&
                    adapter.itemCount < totalCount
                ){
                    offset += 100
                    getComics(offset)

                }
            }
        })
    }

    private fun setData(){
        adapter.differ.submitList(comicsList)
    }
    private fun searchViewListener(){
        binding.searchView.setOnQueryTextListener(object  : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(msg: String): Boolean {
                filter(msg)
                return false
            }
        })
    }

    private fun filter(text : String?){

        val filteredlist: ArrayList<ComicsResults> = ArrayList()

        for (item in comicsList) {
            if (item.title?.lowercase(Locale.getDefault())?.contains(text!!.lowercase(Locale.getDefault()))!!) {
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(requireContext(), "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            adapter.differ.submitList(filteredlist)
        }
    }
}