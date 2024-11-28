package com.example.a2024_danpoon_mesh.model

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("major")
    val major: String
)