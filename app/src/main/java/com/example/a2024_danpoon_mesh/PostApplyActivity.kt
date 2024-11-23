package com.example.a2024_danpoon_mesh

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a2024_danpoon_mesh.databinding.ActivityPostApplyBinding

class PostApplyActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPostApplyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostApplyBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}