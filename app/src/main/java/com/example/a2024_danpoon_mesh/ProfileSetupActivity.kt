package com.example.a2024_danpoon_mesh

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ProfileSetupActivity : AppCompatActivity() {

    private lateinit var etNickname: EditText
    private lateinit var txNicknameErrorMsg: TextView
    private lateinit var etMajor: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_setup)

        // UI 요소 초기화
        etNickname = findViewById(R.id.et_nickname)
        txNicknameErrorMsg = findViewById(R.id.tx_nickname_error_msg)
        etMajor = findViewById(R.id.et_major)

        // 닉네임 유효성 검사
        etNickname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
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

        // 전공 입력 후 알림창 띄우기
        findViewById<TextView>(R.id.tx_set_major).setOnClickListener {
            if (etNickname.text.isNullOrEmpty()) {
                showAlertDialog("닉네임을 입력해주세요.")
            } else if (etMajor.text.isNullOrEmpty()) {
                showAlertDialog("전공을 입력해주세요.")
            } else {
                showAlertDialog("프로필이 설정되었습니다.")
            }
        }
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