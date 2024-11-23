package com.example.a2024_danpoon_mesh

import android.app.Application
import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import com.kakao.sdk.common.KakaoSdk

// CustomApplication 클래스: 앱 전체의 전역 상태를 관리하며 Kakao SDK 초기화를 담당.
class CustomApplication : Application() {

    // companion object는 Java의 static 역할을 수행하며, 앱의 전역 인스턴스를 관리.
    companion object {
        // CustomApplication의 싱글톤 인스턴스 (volatile 키워드는 멀티스레드 환경에서 안전한 접근을 보장).
        @Volatile
        private var instance: CustomApplication? = null

        // 애플리케이션의 전역 Context를 반환.
        // instance가 null이면 IllegalStateException을 발생시킴.
        fun getGlobalApplicationContext(): CustomApplication {
            return instance
                ?: throw IllegalStateException("This application does not inherit com.kakao.GlobalApplication")
        }
    }

    // 애플리케이션 초기화 시 호출되는 메서드.
    // 여기서 Kakao SDK를 초기화하고, 앱의 전역 인스턴스를 설정.
    override fun onCreate() {
        super.onCreate()
        instance = this // 현재 애플리케이션의 인스턴스를 저장.

        KakaoSdk.init(this, "3842028e5ec9809bb1af3bc11dd1f0d2") // Kakao SDK 초기화.
    }

    // 애플리케이션 전역 Context 반환
    fun getGlobalApplicationContext(): Context {
        return applicationContext
    }

    // 애플리케이션 종료 시 호출되는 메서드.
    // 전역 인스턴스를 null로 설정하여 메모리 누수 방지.
    override fun onTerminate() {
        super.onTerminate()
        instance = null
    }
}