package com.example.a2024_danpoon_mesh

import android.content.Intent
import android.content.pm.PackageManager
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
                    // 로그인 성공 후 사용자 정보 받아오기
                    getUserProfile(token?.accessToken)
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
                Log.d("KakaoLogin", "카카오 계정 로그인 성공: ${token?.accessToken}")
                // 로그인 성공 후 사용자 정보 받아오기
                getUserProfile(token?.accessToken)
            }
        }
    }

    // 사용자 프로필 정보를 가져오는 함수
    private fun getUserProfile(accessToken: String?) {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e("KakaoLogin", "사용자 정보 가져오기 실패: $error")
            } else {
                user?.let {
                    // 카카오 계정으로부터 프로필과 이메일을 받아오기
                    val nickname = it.kakaoAccount?.profile?.nickname
                    val major =
                        it.kakaoAccount?.profile?.nickname // 예시: major를 다른 방법으로 받거나 서버에서 받아옵니다

                    // 로그로 출력
                    Log.d("KakaoLogin", "사용자 프로필: ${it.kakaoAccount?.profile}")
                    Log.d("KakaoLogin", "사용자 이메일: ${it.kakaoAccount?.email}")
                    Log.d("KakaoLogin", "사용자 닉네임: $nickname")
                    Log.d("KakaoLogin", "사용자 전공: $major")

                    // 서버로 전송하거나 다른 화면으로 넘길 수 있습니다
                    navigateToProfileSetup(nickname, major)
                }
            }
        }
    }

    // 프로필 설정 화면으로 이동하는 함수
    private fun navigateToProfileSetup(nickname: String?, major: String?) {
        val intent = Intent(this, ProfileSetupActivity::class.java)
        // Intent에 nickname과 major를 추가하여 넘깁니다.
        intent.putExtra("nickname", nickname)
        intent.putExtra("major", major)
        startActivity(intent)
        finish() // 로그인 화면을 종료
    }
}