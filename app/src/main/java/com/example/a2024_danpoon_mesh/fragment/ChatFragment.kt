package com.example.mesh.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a2024_danpoon_mesh.Chat
import com.example.a2024_danpoon_mesh.SearchResult
import com.example.a2024_danpoon_mesh.SearchResultRVAdapter
import com.example.a2024_danpoon_mesh.chatRVAdapter
import com.example.a2024_danpoon_mesh.databinding.FragmentChatBinding

class ChatFragment : Fragment() {
    private lateinit var binding: FragmentChatBinding
    private lateinit var adapter : chatRVAdapter
    private val chatroomList = ArrayList<Chat>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater, container, false)

        initRecyclerView()

        return binding.root
    }

    private fun initRecyclerView() {
        adapter = chatRVAdapter(chatroomList)
        binding.chatChattingRv.adapter = adapter
        binding.chatChattingRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
    }
}