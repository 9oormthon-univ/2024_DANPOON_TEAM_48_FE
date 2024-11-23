package com.example.mesh.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a2024_danpoon_mesh.databinding.FragmentMarkBinding


class MarkFragment : Fragment() {
    private lateinit var binding: FragmentMarkBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMarkBinding.inflate(inflater, container, false)

        return binding.root
    }
}