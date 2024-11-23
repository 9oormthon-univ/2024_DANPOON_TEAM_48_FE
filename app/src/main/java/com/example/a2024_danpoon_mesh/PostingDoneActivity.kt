package com.example.a2024_danpoon_mesh

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a2024_danpoon_mesh.databinding.ActivityPostDoneBinding

class PostingDoneActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostDoneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDoneBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}