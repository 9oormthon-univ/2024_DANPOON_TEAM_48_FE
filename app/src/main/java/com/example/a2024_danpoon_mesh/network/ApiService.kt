package com.example.a2024_danpoon_mesh.network

import com.example.a2024_danpoon_mesh.model.SignUpRequest
import com.example.a2024_danpoon_mesh.model.SignUpResponse
import com.example.a2024_danpoon_mesh.model.UserProfileResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("/api/v1/auth/signup/kakao")
    fun signUp(@Body signUpRequest: SignUpRequest): Call<SignUpResponse>

    @GET("/api/v1/auth/login/kakao")
    fun getUserProfile(@Header("Authorization") token: String): Call<UserProfileResponse>
}

//interface ApiService {
//
//    @POST("/api/v1/chat/room")
//    fun postChatRoom(
//        @Query("requesterId") requesterId: Int,
//        @Query("postId") postId: Int
//    ): Call<postChatRoomResponse>
//
//    @Multipart
//    @POST("/api/v1/posts")
//    fun submitPosting(
//        @Part("postRequest") postRequest: RequestBody, // JSON 데이터
//        @Part projectFile: MultipartBody.Part?,       // 첨부 파일
//        @Part projectImage: MultipartBody.Part?       // 이미지 파일
//    ): Response<PostingResponse>
//}
//
//data class UserProfileResponse(
//    val message: String,
//    val statusCode: Int,
//    val data: String
//)