package com.example.a2024_danpoon_mesh

import android.graphics.Paint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a2024_danpoon_mesh.databinding.ActivityProfileSetupDoneBinding

class ProfileSetupDoneActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileSetupDoneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileSetupDoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txGoMain.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }
}