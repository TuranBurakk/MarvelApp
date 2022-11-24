package com.example.marvelapp.ui.Home

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.base.BaseFragment
import com.example.marvelapp.data.entity.ResultsData
import com.example.marvelapp.databinding.FragmentHomeBinding
import com.example.marvelapp.utils.Constants
import com.example.marvelapp.utils.Resource
import com.example.marvelapp.utils.showDialog
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel : HomeFragmentViewModel by viewModels()
    private val adapter by lazy { HomeAdapter() }
    private var totalCount = 0
    private var offset = Constants.offset
    private val heroList = arrayListOf<ResultsData>()

    override fun onStart() {
        super.onStart()
        showBottomBar()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.heroRecyclerView.adapter = adapter
        binding.heroRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        getHero(offset)
        searchViewListener()
        onScrollListener()
    }

    private fun getHero(offset : Int){
        viewModel.getHero(offset).observe(viewLifecycleOwner){response ->
            when(response.status){

                Resource.Status.SUCCESS ->{
                    totalCount = response.data?.characters?.total ?: 0
                    heroList.addAll(response.data?.characters?.results ?: arrayListOf())
                    val scrollDistance = heroList.size
                    binding.heroRecyclerView.scrollToPosition(scrollDistance)
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
        binding.heroRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!binding.heroRecyclerView.canScrollVertically(1)&&
                        newState == RecyclerView.SCROLL_STATE_IDLE &&
                            heroList.size < totalCount
                ){
                    offset += 100
                    getHero(offset)
                }
            }
        })
    }

    private fun setData(){
        adapter.differ.submitList(heroList)
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

        val filteredlist: ArrayList<ResultsData> = ArrayList()

        for (item in heroList) {
            if (item.name?.lowercase(Locale.getDefault())?.contains(text!!.lowercase(Locale.getDefault()))!!) {
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