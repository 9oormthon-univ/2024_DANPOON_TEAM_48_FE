package com.example.a2024_danpoon_mesh.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "http://15.165.148.169"
    private var accessToken: String? = null

    // 액세스 토큰 설정
    fun setAccessToken(token: String) {
        accessToken = token
    }

    // 액세스 토큰 가져오기
    fun getAccessToken(): String? {
        return accessToken
    }

    // 토큰 삭제 (로그아웃 시 사용)
    fun clearAccessToken() {
        accessToken = null
    }

    // OkHttpClient의 인터셉터를 통해 Authorization 헤더에 액세스 토큰 추가
    private val interceptor = Interceptor { chain ->
        var request = chain.request()

        accessToken?.let {
            request = request.newBuilder()
                .addHeader("Authorization", "Bearer $it")
                .build()
        } ?: run {
            println("AccessToken is null, no Authorization header will be added.")
        }

        chain.proceed(request)
    }

    // OkHttpClient 설정
    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    // Retrofit API Service 초기화
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }
}