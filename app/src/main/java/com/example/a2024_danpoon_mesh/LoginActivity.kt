package com.example.a2024_danpoon_mesh

import android.graphics.Paint.UNDERLINE_TEXT_FLAG
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a2024_danpoon_mesh.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLoginKakao.setOnClickListener {
            loginKakao()
        }

        binding.tvLoginAlready.paintFlags = UNDERLINE_TEXT_FLAG
    }

    private fun loginKakao() {
        // 로그인 정보 확인
    }
}