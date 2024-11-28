package com.example.a2024_danpoon_mesh

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.a2024_danpoon_mesh.databinding.ActivityLoginBinding
import com.example.a2024_danpoon_mesh.model.UserProfileResponse
import com.example.a2024_danpoon_mesh.network.RetrofitClient
import com.example.a2024_danpoon_mesh.network.RetrofitClient.getAccessToken
import com.example.a2024_danpoon_mesh.network.RetrofitClient.setAccessToken
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

        // 'txAlreadyAccount' 버튼 클릭 시 기존 계정으로 로그인
        binding.txAlreadyAccount.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        binding.txAlreadyAccount.setOnClickListener {
            loginAccount()
        }
    }

    // 기존 계정으로 로그인하는 함수
    private fun loginAccount() {
        UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
            if (error != null) {
                Log.e("KakaoLogin", "카카오 계정 로그인 실패: $error")
            } else {
                token?.let {
                    Log.d("KakaoLogin", "카카오 계정 로그인 성공: ${it.accessToken}")

                    // 로그인 성공 시 액세스 토큰을 Retrofit에 설정
                    setAccessToken(it.accessToken)

                    // 로그인 후 사용자 정보 확인
                    checkAccountAndNavigate()
                }
            }
        }
    }

    // 카카오톡으로 로그인
    private fun loginKakao() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                if (error != null) {
                    Log.e("KakaoLogin", "카카오톡 로그인 실패: $error")
                    loginWithKakaoAccount() // 카카오 계정으로 로그인 시도
                } else {
                    Log.d("KakaoLogin", "카카오톡 로그인 성공: ${token?.accessToken}")
                    checkAccountAndNavigate() // 카카오톡 로그인 성공 후 사용자 정보 확인
                }
            }
        } else {
            loginWithKakaoAccount() // 카카오톡 로그인이 불가능하면 계정으로 로그인
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

                    setAccessToken(it.accessToken) // 액세스 토큰 설정

                    // 로그인 성공 후 사용자 정보 확인
                    checkAccountAndNavigate()
                }
            }
        }
    }

    // 사용자 정보 확인 후 화면 이동 결정
    private fun checkAccountAndNavigate() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e("KakaoLogin", "사용자 정보 요청 실패: $error")
            } else if (user != null) {
                Log.d("KakaoLogin", "사용자 정보 요청 성공: ${user.kakaoAccount?.email}")

                // 사용자 정보 확인 후 프로필 설정 화면 또는 메인 화면으로 이동
                isNewUser { isNew ->
                    if (isNew) {
                        moveToProfileSetup() // 새로운 사용자라면 프로필 설정 화면으로 이동
                    } else {
                        moveToMainActivity() // 기존 사용자라면 메인 화면으로 이동
                    }
                }
            }
        }
    }

    // 새로운 사용자 여부 확인 함수 (비동기 호출)
    private fun isNewUser(callback: (Boolean) -> Unit) {
        val token = "Bearer ${getAccessToken()}" // 저장된 액세스 토큰 사용

        RetrofitClient.apiService.getUserProfile(token)
            .enqueue(object : Callback<UserProfileResponse> {
                override fun onResponse(
                    call: Call<UserProfileResponse>,
                    response: Response<UserProfileResponse>
                ) {
                    if (response.isSuccessful) {
                        val userProfileResponse = response.body()
                        if (userProfileResponse != null) {
                            when (userProfileResponse.statusCode) {
                                404 -> {
                                    Log.d("UserCheck", "새로운 사용자입니다.")
                                    callback(true) // 새로운 사용자
                                }

                                200 -> {
                                    Log.d("UserCheck", "기존 사용자입니다.")
                                    callback(false) // 기존 사용자
                                }

                                else -> {
                                    Log.e(
                                        "UserCheck",
                                        "예상치 못한 상태 코드: ${userProfileResponse.statusCode}"
                                    )
                                    callback(false) // 기본값: 기존 사용자로 처리
                                }
                            }
                        } else {
                            Log.e("UserCheck", "응답 데이터가 비어있음")
                            callback(false) // 기본값: 기존 사용자로 처리
                        }
                    } else {
                        Log.e("UserCheck", "사용자 프로필 요청 실패: ${response.errorBody()?.string()}")
                        callback(false) // 기본값: 기존 사용자로 처리
                    }
                }

                override fun onFailure(call: Call<UserProfileResponse>, t: Throwable) {
                    Log.e("UserCheck", "사용자 프로필 요청 중 오류 발생: ${t.message}")
                    callback(false) // 네트워크 오류 시 기본값: 기존 사용자로 처리
                }
            })
    }

    // 프로필 설정 화면으로 이동
    private fun moveToProfileSetup() {
        val intent = Intent(this, ProfileSetupActivity::class.java)
        startActivity(intent)
        finish() // 현재 액티비티 종료
    }

    // 메인 화면으로 이동
    private fun moveToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // 현재 액티비티 종료
    }

    // 앱 키 해시를 가져오는 함수
    private fun getAppKeyHash() {
        try {
            val info =
                packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNING_CERTIFICATES)
            for (signature in info.signingInfo.apkContentsSigners) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val hashKey = Base64.getEncoder().encodeToString(md.digest())
                Log.e("Hash key", hashKey)
            }
        } catch (e: Exception) {
            Log.e("name not found", e.toString())
        }
    }
}