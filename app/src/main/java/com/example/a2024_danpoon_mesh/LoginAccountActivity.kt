package com.example.a2024_danpoon_mesh

import UserProfileResponse
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginAccountActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "LoginAccountActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_account)
        loginAccount()
    }

    // 카카오 계정으로 로그인
    private fun loginAccount() {
        UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
            if (error != null) {
                Log.e(TAG, "카카오 계정 로그인 실패: $error")
            } else {
                token?.let {
                    Log.d(TAG, "카카오 계정 로그인 성공: ${it.accessToken}")
                    fetchUserProfileFromServer(it.accessToken)
                }
            }
        }
    }

    // 서버에서 사용자 정보를 가져오는 함수
    private fun fetchUserProfileFromServer(accessToken: String) {
        RetrofitClient.apiService.getUserProfile("Bearer $accessToken")
            .enqueue(object : Callback<UserProfileResponse> {
                override fun onResponse(
                    call: Call<UserProfileResponse>,
                    response: Response<UserProfileResponse>
                ) {
                    if (response.isSuccessful) {
                        val userProfile = response.body()?.data
                        if (userProfile != null) {
                            Log.d(TAG, "${userProfile}")
                            navigateToMainActivity(userProfile)
                        } else {
                            showAlertDialog("사용자 정보를 가져오는 데 실패했습니다.")
                        }
                    } else {
                        showAlertDialog("서버와의 통신에 실패했습니다. 응답 코드: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<UserProfileResponse>, t: Throwable) {
                    showAlertDialog("서버와 연결할 수 없습니다: ${t.message}")
                }
            })
    }

    // 사용자 정보를 가져온 후 MainActivity로 이동
    private fun navigateToMainActivity(data:String) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("data", data)
        }
        startActivity(intent)
        finish() // 현재 액티비티 종료
    }

    // 알림창 표시
    private fun showAlertDialog(message: String) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("확인", null)
            .show()
    }
}