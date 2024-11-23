package com.example.a2024_danpoon_mesh

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.a2024_danpoon_mesh.model.SignUpRequest
import com.example.a2024_danpoon_mesh.model.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileSetupActivity : AppCompatActivity() {

    private lateinit var etNickname: EditText
    private lateinit var txNicknameErrorMsg: TextView
    private lateinit var etMajor: EditText
    private lateinit var btnSave: ImageView  // 이미지 뷰로 버튼을 설정

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_setup)

        // UI 요소 초기화
        etNickname = findViewById(R.id.et_nickname)
        txNicknameErrorMsg = findViewById(R.id.tx_nickname_error_msg)
        etMajor = findViewById(R.id.et_major)
        btnSave = findViewById(R.id.btn_save)

        // 닉네임 유효성 검사
        etNickname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                charSequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (!charSequence.isNullOrEmpty()) {
                    validateNickname(charSequence.toString())
                } else {
                    txNicknameErrorMsg.visibility = TextView.INVISIBLE
                }
            }

            override fun afterTextChanged(editable: Editable?) {}
        })

        // 엔터키로 줄바꿈이 되지 않도록 설정
        etNickname.setImeOptions(EditorInfo.IME_ACTION_DONE)
        etNickname.setSingleLine(true)

        // 확인 버튼 클릭 시 서버로 데이터 전송
        btnSave.setOnClickListener {
            val nickname = etNickname.text.toString()
            val major = etMajor.text.toString()

            if (nickname.isEmpty()) {
                showAlertDialog("닉네임을 입력해주세요.")
            } else if (major.isEmpty()) {
                showAlertDialog("전공을 입력해주세요.")
            } else {
                val signUpRequest = SignUpRequest(nickname, major)

                // Retrofit을 사용해 서버에 데이터 전송
                RetrofitClient.apiService.signUp(signUpRequest)
                    .enqueue(object : Callback<SignUpResponse> {
                        override fun onResponse(
                            call: Call<SignUpResponse>,
                            response: Response<SignUpResponse>
                        ) {
                            if (response.isSuccessful) {
                                val signUpResponse = response.body()
                                if (signUpResponse?.statusCode == 200) {
                                    showAlertDialog(getString(R.string.success_set_profile))
                                    goToMain()
                                } else {
                                    showAlertDialog("프로필 설정 실패: ${signUpResponse?.message}")
                                }
                            } else {
                                showAlertDialog("서버와의 통신에 실패했습니다.")
                            }
                        }

                        override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                            showAlertDialog("서버에 연결할 수 없습니다: ${t.message}")
                        }
                    })
            }
        }
    }

    private fun goToMain() {
        val intent =
            Intent(this@ProfileSetupActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    // 닉네임 유효성 검사
    private fun isNicknameValid(nickname: String): Boolean {
        val regex = "^[a-z0-9]{4,10}$".toRegex() // 영문 소문자 + 숫자, 4~10자
        return nickname.matches(regex) // 정규식 검사
    }

    private fun validateNickname(nickname: String) {
        if (isNicknameValid(nickname)) {
            txNicknameErrorMsg.text = "사용 가능한 닉네임입니다."
        } else {
            txNicknameErrorMsg.text = "사용 불가능한 닉네임입니다."
        }
        txNicknameErrorMsg.visibility = TextView.VISIBLE
    }

    // 알림창을 띄우는 함수
    private fun showAlertDialog(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
            .setPositiveButton("확인") { dialog, _ ->
                dialog.dismiss() // 확인 버튼을 누르면 다이얼로그가 닫히게 함
            }

        val dialog = builder.create()
        dialog.show() // 알림창 띄우기
    }
}