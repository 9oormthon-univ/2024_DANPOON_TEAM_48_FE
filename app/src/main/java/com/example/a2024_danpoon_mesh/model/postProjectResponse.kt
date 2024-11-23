package com.example.a2024_danpoon_mesh.model

data class postProjectResponse(
    val projectTitle: String,
    val projectContents: String,
    val deadline: String,
    val pmBest: String,
    val designBest: String,
    val backBest: String,
    val frontBest: String,
    val pmCategory: List<String>,
    val designCategory: List<String>,
    val backCategory: List<String>,
    val frontCategory: List<String>,
    val status: String
)