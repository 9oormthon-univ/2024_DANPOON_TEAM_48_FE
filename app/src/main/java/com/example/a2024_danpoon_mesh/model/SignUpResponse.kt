package com.example.a2024_danpoon_mesh.model

import android.provider.ContactsContract.RawContacts.Data

data class SignUpResponse(
    val message: String,
    val statusCode: Int,
    val data: UserData
)

data class UserData(
    val userId: Int
)