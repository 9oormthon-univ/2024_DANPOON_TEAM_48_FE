package com.example.a2024_danpoon_mesh

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a2024_danpoon_mesh.databinding.FragmentSearchResultBinding

class SearchResultFragment : Fragment() {

    private lateinit var binding : FragmentSearchResultBinding
    private lateinit var adapter : SearchResultRVAdapter
    private val searchResultList = ArrayList<SearchResult>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchResultBinding.inflate(inflater,container,false)

        initRecyclerView()

        return binding.root
    }

    private fun initRecyclerView() {
        adapter = SearchResultRVAdapter(searchResultList)
        binding.searchResultRv.adapter = adapter
        binding.searchResultRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
    }

}