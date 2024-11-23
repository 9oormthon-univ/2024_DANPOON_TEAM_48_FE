package com.example.a2024_danpoon_mesh

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater

class CustomDialog(private val context: Context) {

    fun showCustomAlertDialog() {
        // 커스텀 레이아웃을 인플레이트
        val inflater = LayoutInflater.from(context)
        val dialogView = inflater.inflate(R.layout.dialog_set_profile_custom, null)

        // AlertDialog 빌더를 사용해 다이얼로그 만들기
        val dialogBuilder = AlertDialog.Builder(context)
            .setView(dialogView) // 커스텀 뷰 적용
            .setCancelable(false) // 다이얼로그 바깥 부분 클릭으로 닫히지 않게

        val dialog = dialogBuilder.create()

        // 다이얼로그 표시
        dialog.show()
    }
}