package com.example.mesh.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a2024_danpoon_mesh.SearchActivity
import com.example.a2024_danpoon_mesh.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.homeSearchIv.setOnClickListener {
            val i = Intent(requireContext(),SearchActivity::class.java)
            startActivity(i)
        }

        return binding.root
    }
}