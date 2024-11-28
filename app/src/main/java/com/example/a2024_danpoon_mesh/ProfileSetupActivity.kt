package com.example.a2024_danpoon_mesh

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.a2024_danpoon_mesh.model.SignUpRequest
import com.example.a2024_danpoon_mesh.model.SignUpResponse
import com.example.a2024_danpoon_mesh.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileSetupActivity : AppCompatActivity() {

    private lateinit var etNickname: EditText
    private lateinit var txNicknameErrorMsg: TextView
    private lateinit var etMajor: EditText
    private lateinit var txMajorErrorMsg: TextView
    private lateinit var btnSave: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_setup)

        // UI 요소 초기화
        etNickname = findViewById(R.id.et_nickname)
        txNicknameErrorMsg = findViewById(R.id.tx_nickname_error_msg)
        etMajor = findViewById(R.id.et_major)
        txMajorErrorMsg = findViewById(R.id.tx_major_error_msg)
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
                    if (isNicknameValid(charSequence.toString())) {
                        txNicknameErrorMsg.text = "사용 가능한 닉네임입니다."
                    } else {
                        txNicknameErrorMsg.text = "사용 불가능한 닉네임입니다."
                    }
                    txNicknameErrorMsg.visibility = TextView.VISIBLE
                } else {
                    txNicknameErrorMsg.visibility = TextView.INVISIBLE
                }
            }

            override fun afterTextChanged(editable: Editable?) {}
        })

        // 전공 유효성 검사
        etMajor.addTextChangedListener(object : TextWatcher {
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
                if (charSequence.isNullOrEmpty()) {
                    txMajorErrorMsg.visibility = TextView.INVISIBLE
                } else {
                    txMajorErrorMsg.visibility = TextView.INVISIBLE
                }
            }

            override fun afterTextChanged(editable: Editable?) {}
        })

        // 엔터키로 줄바꿈이 되지 않도록 설정
        etNickname.setImeOptions(EditorInfo.IME_ACTION_DONE)
        etNickname.setSingleLine(true)
        etMajor.setImeOptions(EditorInfo.IME_ACTION_DONE)
        etMajor.setSingleLine(true)

        // 확인 버튼 클릭 시 유효성 검사 완료 후 다이얼로그 표시
        btnSave.setOnClickListener {
            // 닉네임과 전공 입력값 가져오기
            val nickname = etNickname.text.toString()
            val major = etMajor.text.toString()

            // 닉네임 유효성 검사
            if (!isNicknameValid(nickname)) {
                txNicknameErrorMsg.text = "사용 불가능한 닉네임입니다."
                txNicknameErrorMsg.visibility = TextView.VISIBLE
            }
            // 전공 유효성 검사
            else if (major.isEmpty()) {
                txMajorErrorMsg.text = "전공을 입력해주세요."
                txMajorErrorMsg.visibility = TextView.VISIBLE
            } else {
                // 유효성 검사 통과 후 다이얼로그 표시
                val dialogView = layoutInflater.inflate(R.layout.custom_dialog_profile_setup, null)

                // 커스텀 다이얼로그 객체 생성
                val dialog = AlertDialog.Builder(this)
                    .setView(dialogView) // 커스텀 레이아웃 설정
                    .create()

                // 다이얼로그 표시
                dialog.show()

                // 다이얼로그 내 "확인" 버튼 클릭 시 서버로 데이터 전송 및 이동
                val btnDialogSave: ImageView = dialogView.findViewById(R.id.btn_dialog_save)
                btnDialogSave.setOnClickListener {
                    // 닉네임과 전공 가져오기
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
                                        // 프로필 설정 성공 후 ProfileSetupDoneActivity로 이동
                                        val intent = Intent(
                                            this@ProfileSetupActivity,
                                            ProfileSetupDoneActivity::class.java
                                        )
                                        startActivity(intent)
                                        finish()
                                    } else {
                                        Toast.makeText(
                                            this@ProfileSetupActivity,
                                            "프로필 설정 실패: ${signUpResponse?.message}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                } else {
                                    // 서버 통신 실패 시 토스트 메시지
                                    Toast.makeText(
                                        this@ProfileSetupActivity,
                                        "서버와의 통신에 실패했습니다.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                                // 서버 요청 실패 시 토스트 메시지 띄우기
                                Toast.makeText(
                                    this@ProfileSetupActivity,
                                    "서버에 연결할 수 없습니다: ${t.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        })
                    dialog.dismiss()  // 다이얼로그 닫기
                }

                // "뒤로 가기" 버튼 클릭 시 다이얼로그 닫기
                val btnDialogBack: ImageView = dialogView.findViewById(R.id.btn_dialog_back)
                btnDialogBack.setOnClickListener {
                    dialog.dismiss()
                }
            }
        }
    }

    // 닉네임 유효성 검사
    private fun isNicknameValid(nickname: String): Boolean {
        val regex = "^[a-z0-9]{4,10}$".toRegex() // 영문 소문자 + 숫자, 4~10자
        return nickname.matches(regex) // 정규식 검사
    }
}