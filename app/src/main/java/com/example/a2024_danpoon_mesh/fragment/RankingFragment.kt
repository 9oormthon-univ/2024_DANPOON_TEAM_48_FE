package com.example.mesh.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a2024_danpoon_mesh.Ranking
import com.example.a2024_danpoon_mesh.RankingRVAdapter
import com.example.a2024_danpoon_mesh.databinding.FragmentRankingBinding


class RankingFragment : Fragment() {

    private lateinit var binding: FragmentRankingBinding
    private lateinit var adapter : RankingRVAdapter
    private val rankingList = ArrayList<Ranking>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRankingBinding.inflate(inflater, container, false)

        initRecyclerView()

        return binding.root
    }

    private fun initRecyclerView() {
        adapter = RankingRVAdapter(rankingList)
        binding.rankingRankRv.adapter = adapter
        binding.rankingRankRv.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
    }
}