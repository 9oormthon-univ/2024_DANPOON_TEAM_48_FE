package com.example.a2024_danpoon_mesh

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a2024_danpoon_mesh.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchBackBtnIv.setOnClickListener {
            finish()
        }

        binding.searchKeywordSetBtnIv.setOnClickListener {
            val i = Intent(this,SearchKeywordActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}