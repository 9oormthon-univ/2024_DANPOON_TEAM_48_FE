package com.example.a2024_danpoon_mesh

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.helper.widget.Flow
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.setPadding
import com.example.a2024_danpoon_mesh.databinding.ActivitySearchKeywordBinding
import com.google.android.flexbox.FlexboxLayout

class SearchKeywordActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySearchKeywordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchKeywordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupFlexbox()
    }

    private fun setupFlexbox() {
        val pmTags = listOf("# 일반 기획","# 서비스 기획","# PM","# 데이터 분석")
        val designTags = listOf("# UI 디자인","# UX 디자인","# 웹 디자인","# 그래픽 디자인","# 브랜드 디자인")
        val feTags = listOf("# React","# Vue","# Angular","# Svelte","# JQuery","# Ember","# Backbone",
            "# Semantic UI","# Foundation","# Preact")
        val beTags = listOf("# Java","# Python","# Javascript","# Ruby","# PHP","# Spring","# Django","# Express")


        for (tag in pmTags) {
            val textView = createTagTextView(tag)
            binding.searchKeywordPmFb.addView(textView)
        }

        for (tag in designTags) {
            val textView = createTagTextView(tag)
            binding.searchKeywordDesignFb.addView(textView)
        }

        for (tag in feTags) {
            val textView = createTagTextView(tag)
            binding.searchKeywordFeFb.addView(textView)
        }

        for (tag in beTags) {
            val textView = createTagTextView(tag)
            binding.searchKeywordBeFb.addView(textView)
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun createTagTextView(tag: String): TextView {
        return TextView(this).apply {
            text = tag
            setBackgroundResource(R.drawable.bg_flow_item) // 태그 배경 (XML에서 설정)
            setPadding(30, 10, 30, 10) // 패딩 설정
            setTextColor(R.color.font_subsub)
            textSize = 14f // 텍스트 크기
            layoutParams = FlexboxLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                marginEnd = 36
                topMargin = 24
            }
        }
    }
}