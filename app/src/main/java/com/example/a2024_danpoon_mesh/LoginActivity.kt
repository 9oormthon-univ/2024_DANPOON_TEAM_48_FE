package com.example.a2024_danpoon_mesh

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.a2024_danpoon_mesh.databinding.ActivityLoginBinding
import com.kakao.sdk.user.UserApiClient
import java.security.MessageDigest
import java.util.Base64

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getAppKeyHash()

        binding.btnLoginKakao.setOnClickListener {
            loginKakao()
        }

        binding.txAlreadyAccount.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        binding.txAlreadyAccount.setOnClickListener {
            val intent = Intent(this, LoginAccountActivity::class.java)
            startActivity(intent)
        }
    }

    // 앱 키 해시를 가져오는 함수
    private fun getAppKeyHash() {
        try {
            val info =
                packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNING_CERTIFICATES)
            for (signature in info.signingInfo.apkContentsSigners) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())

                // Kotlin 표준 Base64 사용
                val hashKey = Base64.getEncoder().encodeToString(md.digest())
                Log.e("Hash key", hashKey)
            }
        } catch (e: Exception) {
            Log.e("name not found", e.toString())
        }
    }

    // 카카오 로그인 처리 함수
    private fun loginKakao() {
        // 카카오톡 로그인 가능 여부 확인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            // 카카오톡으로 로그인
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                if (error != null) {
                    Log.e("KakaoLogin", "카카오톡 로그인 실패: $error")
                    // 카카오 계정으로 로그인 시도
                    loginWithKakaoAccount()
                } else {
                    Log.d("KakaoLogin", "카카오톡 로그인 성공: ${token?.accessToken}")
                    // 로그인 성공 시 프로필 설정 화면으로 이동하고 사용자 정보 가져오기
                    getUserProfile()
                }
            }
        } else {
            // 카카오톡 로그인이 불가능하면 카카오 계정으로 로그인 시도
            loginWithKakaoAccount()
        }
    }

    // 카카오 계정으로 로그인
    private fun loginWithKakaoAccount() {
        UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
            if (error != null) {
                Log.e("KakaoLogin", "카카오 계정 로그인 실패: $error")
            } else {
                token?.let {
                    Log.d("KakaoLogin", "카카오 계정 로그인 성공: ${it.accessToken}")

                    // 로그인 성공 시 프로필 설정 화면으로 이동하고 사용자 정보 가져오기
                    // 카카오 로그인 후 받은 액세스 토큰을 Retrofit에 설정
                    RetrofitClient.setAccessToken(it.accessToken) // 액세스 토큰 설정

                    // 사용자 프로필 가져오기
                    getUserProfile()
                }
            }
        }
    }

    // 사용자 프로필 정보를 가져오는 함수
    private fun getUserProfile() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e("KakaoLogin", "사용자 정보 가져오기 실패: $error")
            } else {
                user?.let {
                    // 사용자 정보가 성공적으로 가져와지면 프로필, 이메일 로그로 출력
                    Log.d("KakaoLogin", "사용자 프로필: ${it.kakaoAccount?.profile}")
                    Log.d("KakaoLogin", "사용자 이메일: ${it.kakaoAccount?.email}")

                    // 프로필 정보를 얻은 후 프로필 설정 화면으로 이동
                    navigateToProfileSetup()
                }
            }
        }
    }

    // 프로필 설정 화면으로 이동하는 함수
    private fun navigateToProfileSetup() {
        val intent = Intent(this, ProfileSetupActivity::class.java)
        startActivity(intent)
        finish() // 로그인 화면을 종료
    }
}