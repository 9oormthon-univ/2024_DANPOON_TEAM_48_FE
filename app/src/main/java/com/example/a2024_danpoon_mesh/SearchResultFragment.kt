package com.example.a2024_danpoon_mesh

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a2024_danpoon_mesh.databinding.FragmentSearchResultBinding

class SearchResultFragment : Fragment() {

    private lateinit var binding : FragmentSearchResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchResultBinding.inflate(inflater,container,false)

        return binding.root
    }

}