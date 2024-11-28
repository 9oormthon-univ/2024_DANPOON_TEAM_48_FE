package com.example.a2024_danpoon_mesh

import HomeFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a2024_danpoon_mesh.databinding.ActivityMainBinding
//import com.example.mesh.Fragment.ChatFragment
import com.example.mesh.Fragment.MarkFragment
import com.example.mesh.Fragment.MyFragment
import com.example.mesh.Fragment.RankingFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        BottomNavigation()
    }

    private fun BottomNavigation() {

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.menu_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.menu_ranking -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, RankingFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.menu_mark -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, MarkFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

//                R.id.menu_chat -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.main_frm, ChatFragment())
//                        .commitAllowingStateLoss()
//                    return@setOnItemSelectedListener true
//                }

                R.id.menu_my -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, MyFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}